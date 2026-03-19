package com.f0x1d.logfox.feature.terminals.api.base

sealed interface TerminalType {
    val key: String

    data object Default : TerminalType {
        override val key = "default"
    }

    data object Root : TerminalType {
        override val key = "root"
    }

    data object Shizuku : TerminalType {
        override val key = "shizuku"
    }

    data class Remote(val deviceId: String) : TerminalType {
        override val key = "remote_$deviceId"
    }

    companion object {
        val entries: List<TerminalType> = listOf(Default, Root, Shizuku)

        fun fromKey(key: String): TerminalType = when {
            key == Default.key -> Default
            key == Root.key -> Root
            key == Shizuku.key -> Shizuku
            key.startsWith("remote_") -> Remote(key.removePrefix("remote_"))
            else -> Default
        }
    }
}