package com.f0x1d.logfox.feature.remote.devices.impl.adb

import dadb.AdbKeyPair
import dadb.Dadb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.Closeable
import java.io.InputStream

internal class AdbConnection(
    private val host: String,
    private val port: Int,
) : Closeable {

    private var dadb: Dadb? = null

    val isConnected: Boolean get() = dadb != null

    suspend fun connect() = withContext(Dispatchers.IO) {
        Timber.d("Connecting to $host:$port")
        dadb = Dadb.create(host, port, AdbKeyPair.readDefault())
        Timber.d("Connected to $host:$port")
    }

    suspend fun executeCommand(vararg command: String): AdbShellStream = withContext(Dispatchers.IO) {
        val dadb = dadb ?: throw AdbConnectionException("Not connected")
        val commandString = command.joinToString(" ")
        Timber.d("Executing command: $commandString")

        val response = dadb.shell(commandString)
        AdbShellStream(response)
    }

    override fun close() {
        Timber.d("Closing connection to $host:$port")
        runCatching {
            dadb?.close()
        }
        dadb = null
    }
}

internal class AdbShellStream(
    private val response: dadb.AdbShellResponse,
) : Closeable {

    val output: InputStream get() = response.output.byteInputStream()

    val allOutput: String get() = response.allOutput

    override fun close() = Unit
}

internal class AdbConnectionException(message: String) : Exception(message)