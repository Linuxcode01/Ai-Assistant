package com.example.assistant.service

import com.example.assistant.model.CommandRequest
import com.example.assistant.model.IntentResult
import org.springframework.stereotype.Service

@Service
class IntentService {

    private val regexRules = listOf(
        Rule("CALL", listOf("call", "फोन", "phone")),
        Rule("FLASHLIGHT_ON", listOf("flashlight on", "torch on", "flash on")),
        Rule("FLASHLIGHT_OFF", listOf("flashlight off", "torch off", "flash off")),
        Rule("VOLUME_SET", listOf("volume", "awaz", "sound")),
        Rule("OPEN_APP", listOf("open", "khol", "launch"))
    )

    fun detectIntent(request: CommandRequest): IntentResult {
        val normalized = request.text.lowercase()

        val matched = regexRules.firstOrNull { rule ->
            rule.examples.any { normalized.contains(it) }
        }

        return when (matched?.intent) {
            "CALL" -> IntentResult("CALL", 0.92, extractNameSlot(normalized))
            "OPEN_APP" -> IntentResult("OPEN_APP", 0.88, extractAppSlot(normalized))
            "VOLUME_SET" -> IntentResult("VOLUME_SET", 0.82, extractVolumeSlot(normalized))
            "FLASHLIGHT_ON", "FLASHLIGHT_OFF" -> IntentResult(matched.intent, 0.93)
            else -> IntentResult("GENERAL_QA", 0.55)
        }
    }

    private fun extractNameSlot(text: String): Map<String, String> {
        val after = text.substringAfter("call", "").trim()
        return if (after.isNotBlank()) mapOf("contact" to after) else emptyMap()
    }

    private fun extractAppSlot(text: String): Map<String, String> {
        val after = text.substringAfter("open", "").trim()
        return if (after.isNotBlank()) mapOf("appName" to after) else emptyMap()
    }

    private fun extractVolumeSlot(text: String): Map<String, String> {
        val percent = "(\\d{1,3})".toRegex().find(text)?.value
        return if (percent != null) mapOf("level" to percent) else emptyMap()
    }

    private data class Rule(
        val intent: String,
        val examples: List<String>
    )
}
