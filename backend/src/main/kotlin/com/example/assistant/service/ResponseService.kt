package com.example.assistant.service

import com.example.assistant.model.ActionPlan
import com.example.assistant.model.AssistantReply
import com.example.assistant.model.IntentResult
import org.springframework.stereotype.Service

@Service
class ResponseService(
    private val memoryService: MemoryService
) {

    fun buildReply(userId: String, intent: IntentResult): AssistantReply {
        val language = memoryService.getPreferredLanguage(userId)

        return when (intent.intent) {
            "CALL" -> AssistantReply(
                text = if (language.startsWith("hi")) "ठीक है, कॉल शुरू करता हूँ।" else "Sure, starting the call.",
                actionPlan = ActionPlan("PHONE_CALL", intent.slots)
            )
            "FLASHLIGHT_ON" -> AssistantReply(
                text = if (language.startsWith("hi")) "फ्लैशलाइट ऑन कर दी।" else "Flashlight turned on.",
                actionPlan = ActionPlan("FLASHLIGHT", mapOf("state" to "on"))
            )
            "FLASHLIGHT_OFF" -> AssistantReply(
                text = if (language.startsWith("hi")) "फ्लैशलाइट ऑफ कर दी।" else "Flashlight turned off.",
                actionPlan = ActionPlan("FLASHLIGHT", mapOf("state" to "off"))
            )
            "VOLUME_SET" -> AssistantReply(
                text = if (language.startsWith("hi")) "वॉल्यूम अपडेट कर दिया।" else "Volume updated.",
                actionPlan = ActionPlan("VOLUME", intent.slots)
            )
            "OPEN_APP" -> AssistantReply(
                text = if (language.startsWith("hi")) "ऐप खोल रहा हूँ।" else "Opening app.",
                actionPlan = ActionPlan("OPEN_APP", intent.slots)
            )
            else -> AssistantReply(
                text = if (language.startsWith("hi")) {
                    "मैं आपकी मदद कर सकता हूँ: कॉल, फ्लैशलाइट, वॉल्यूम, और ऐप खोलना।"
                } else {
                    "I can help with calls, flashlight, volume, and opening apps."
                }
            )
        }
    }
}
