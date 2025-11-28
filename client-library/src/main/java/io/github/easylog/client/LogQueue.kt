package io.github.easylog.client

import io.github.easylog.model.LogEntry
import java.util.Queue
import java.util.concurrent.ArrayBlockingQueue

/**
 * @author Peter Szrnka
 */
class LogQueue {

    companion object {
        private val QUEUE: Queue<LogEntry> = ArrayBlockingQueue(1000)

        fun add(message: LogEntry) {
            QUEUE.add(message)
        }

        fun getQueue(): Queue<LogEntry> {
            return QUEUE
        }
    }
}