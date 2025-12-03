package io.github.easylog.client

import android.util.Log
import io.github.easylog.model.LogEntry
import io.github.easylog.model.LogLevel
import java.time.ZonedDateTime
import java.util.UUID

/**
 * @author Peter Szrnka
 */
class EasyLog {

    companion object {

        private var sessionId: String = UUID.randomUUID().toString()

        @JvmStatic
        fun i(tag: String, message: String, metadata: Map<String, String>? = null) {
            Log.i(tag, message)
            log(LogLevel.INFO, tag, message, metadata)
        }

        @JvmStatic
        fun d(tag: String, message: String, metadata: Map<String, String>? = null) {
            Log.d(tag, message)
            log(LogLevel.DEBUG, tag, message, metadata)
        }

        @JvmStatic
        fun w(tag: String, message: String, metadata: Map<String, String>? = null) {
            Log.w(tag, message)
            log(LogLevel.WARN, tag, message, metadata)
        }

        @JvmStatic
        fun e(tag: String, message: String, throwable: Throwable?, metadata: Map<String, String>? = null) {
            Log.e(tag, message, throwable)
            log(LogLevel.ERROR, tag, message, metadata)
        }

        @JvmStatic
        private fun log(logLevel: LogLevel, tag: String, message: String, metadata: Map<String, String>? = null) {
            if (EasyLogState.enabled) {
                LogQueue.add(buildLogEntry(logLevel, tag, message, metadata))
            }
        }

        @JvmStatic
        fun buildLogEntry(logLevel: LogLevel, logTag: String, message: String, metadata: Map<String, String>? = null): LogEntry {
            val logEntry = LogEntry()
            logEntry.messageId = UUID.randomUUID().toString()
            logEntry.message = message
            logEntry.tag = logTag
            logEntry.logLevel = logLevel
            logEntry.sessionId = sessionId
            logEntry.timestamp = ZonedDateTime.now()
            logEntry.metadata = metadata
            return logEntry
        }
    }
}