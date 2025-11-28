package io.github.easylog.client

/**
 * @author Peter Szrnka
 */
interface LogCallback {
    fun log(tag: String, log: String)
}