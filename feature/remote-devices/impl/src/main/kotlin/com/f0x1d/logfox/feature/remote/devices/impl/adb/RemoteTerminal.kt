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

internal class RemoteTerminal(
    private val device: RemoteDevice,
    internal val connection: AdbConnection,
) : Terminal {

    override val type: TerminalType = TerminalType.Remote(device.id.toString())

    override val title: Int = 0 // Will use device name instead

    val deviceName: String get() = device.name

    override suspend fun isSupported(): Boolean = connection.isConnected

    override suspend fun executeNow(vararg command: String): TerminalResult = withContext(Dispatchers.IO) {
        runCatching {
            val stream = connection.executeCommand(*command)
            stream.use {
                val output = it.allOutput
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