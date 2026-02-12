package com.example.phoneassistant.audio

interface WakeWordEngine {
    fun start(onWake: () -> Unit)
    fun stop()
}

interface SpeechToTextEngine {
    suspend fun transcribe(): String
}

interface TextToSpeechEngine {
    fun speak(text: String)
}

class VoicePipeline(
    private val wakeWordEngine: WakeWordEngine,
    private val sttEngine: SpeechToTextEngine,
    private val ttsEngine: TextToSpeechEngine
) {
    fun start(onCommandText: suspend (String) -> Unit) {
        wakeWordEngine.start {
            // In production this callback should launch a coroutine in service scope.
        }
    }

    fun stop() {
        wakeWordEngine.stop()
    }

    fun speak(text: String) {
        ttsEngine.speak(text)
    }
}
