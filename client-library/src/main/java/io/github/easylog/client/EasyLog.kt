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
        fun i(tag: String, message: String) {
            if (!EasyLogState.enabled) {
                return
            }

            Log.i(tag, message)
            LogQueue.add(buildSaveLogRequest(LogLevel.INFO, tag, message))
        }

        @JvmStatic
        fun d(tag: String, message: String) {
            if (!EasyLogState.enabled) {
                return
            }

            Log.d(tag, message)
            LogQueue.add(buildSaveLogRequest(LogLevel.DEBUG, tag, message))
        }

        @JvmStatic
        fun w(tag: String, message: String) {
            if (!EasyLogState.enabled) {
                return
            }

            Log.w(tag, message)
            LogQueue.add(buildSaveLogRequest(LogLevel.WARN, tag, message))
        }

        @JvmStatic
        fun e(tag: String, message: String, throwable: Throwable?) {
            if (!EasyLogState.enabled) {
                return
            }

            Log.e(tag, message, throwable)
            LogQueue.add(buildSaveLogRequest(LogLevel.INFO, tag, message))
        }

        fun buildSaveLogRequest(logLevel: LogLevel, logTag: String, message: String): LogEntry {
            val request = LogEntry()
            request.messageId = UUID.randomUUID().toString()
            request.message = message
            request.tag = logTag
            request.logLevel = logLevel
            request.sessionId = sessionId
            request.timestamp = ZonedDateTime.now()
            return request
        }
    }
}