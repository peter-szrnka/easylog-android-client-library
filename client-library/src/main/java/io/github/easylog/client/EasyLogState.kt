package io.github.easylog.client

/**
 * @author Peter Szrnka
 */
class EasyLogState {

    companion object {
        var enabled: Boolean = true
        var serverFound: Boolean = false
        var serverHost: String? = null
        var metadata: Map<String, String>? = null
        var pollEnabled: Boolean = false
        var discoveryStarted: Boolean = false
    }
}