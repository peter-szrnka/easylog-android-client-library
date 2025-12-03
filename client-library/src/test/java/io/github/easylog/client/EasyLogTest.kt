package io.github.easylog.client

import android.util.Log
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.mockStatic
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class EasyLogTest {

    val TAG = "unitTest"

    @AfterEach
    fun tearDown() {
        LogQueue.getQueue().clear()
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun testI(enable: Boolean) {
        mockStatic(Log::class.java).use { mockedLog ->
            // given
            EasyLogState.enabled = enable

            // when
            EasyLog.i(TAG, "message")

            // then
            assertEquals(enable, !LogQueue.getQueue().isEmpty())
            mockedLog.verify { Log.i(TAG, "message") }
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun testD(enable: Boolean) {
        mockStatic(Log::class.java).use { mockedLog ->
            // given
            EasyLogState.enabled = enable

            // when
            EasyLog.d(TAG, "message", if(enable) mapOf(pair = Pair("username", "john.doe")) else null)

            // then
            assertEquals(enable, !LogQueue.getQueue().isEmpty())
            mockedLog.verify { Log.d(TAG, "message") }
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun testW(enable: Boolean) {
        mockStatic(Log::class.java).use { mockedLog ->
            // given
            EasyLogState.enabled = enable

            // when
            EasyLog.w(TAG, "message")

            // then
            assertEquals(enable, !LogQueue.getQueue().isEmpty())
            mockedLog.verify { Log.w(TAG, "message") }
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun testE(enable: Boolean) {
        mockStatic(Log::class.java).use { mockedLog ->
            // given
            EasyLogState.enabled = enable
            val exception = RuntimeException("Oops!")

            // when
            EasyLog.e(TAG, "message", exception)

            // then
            assertEquals(enable, !LogQueue.getQueue().isEmpty())
            mockedLog.verify { Log.e(TAG, "message", exception) }
        }
    }
}