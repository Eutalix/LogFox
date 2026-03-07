package com.f0x1d.logfox.feature.remote.devices.impl.adb

import com.f0x1d.logfox.feature.remote.devices.api.model.RemoteDevice
import com.f0x1d.logfox.feature.terminals.api.base.Terminal
import com.f0x1d.logfox.feature.terminals.api.base.TerminalType
import com.f0x1d.logfox.feature.terminals.api.model.TerminalProcess
import com.f0x1d.logfox.feature.terminals.api.model.TerminalResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class RemoteTerminal(
    private val device: RemoteDevice,
    private val connection: AdbConnection,
) : Terminal {

    override val type: TerminalType = TerminalType.Remote(device.id.toString())

    override val title: Int = 0 // Will use device name instead

    val deviceName: String get() = device.name

    override suspend fun isSupported(): Boolean = connection.isConnected

    override suspend fun executeNow(vararg command: String): TerminalResult = withContext(Dispatchers.IO) {
        runCatching {
            val stream = connection.executeCommand(*command)
            stream.use {
                val output = it.output.bufferedReader().readText()
                TerminalResult(
                    exitCode = 0,
                    output = output,
                    errorOutput = "",
                )
            }
        }.getOrElse { throwable ->
            Timber.e(throwable, "Error executing command")
            TerminalResult(
                exitCode = 1,
                output = "",
                errorOutput = throwable.message ?: "Unknown error",
            )
        }
    }

    override fun execute(vararg command: String): TerminalProcess? = runCatching {
        val stream = kotlinx.coroutines.runBlocking {
            connection.executeCommand(*command)
        }

        TerminalProcess(
            output = stream.output,
            error = ByteArrayInputStream(ByteArray(0)),
            input = ByteArrayOutputStream(),
            destroy = { stream.close() },
        )
    }.getOrNull()

    override suspend fun exit() {
        connection.close()
    }
}

// Extension for TerminalType
data class Remote(val deviceId: String) : TerminalType {
    override val key: String = "remote_$deviceId"
}

private fun TerminalType.Companion.Remote(deviceId: String): TerminalType = Remote(deviceId)