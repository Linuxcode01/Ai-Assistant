package com.example.assistant.model

import java.time.Instant

data class CommandRequest(
    val userId: String,
    val text: String,
    val language: String = "hi-IN",
    val context: Map<String, String> = emptyMap()
)

data class IntentResult(
    val intent: String,
    val confidence: Double,
    val slots: Map<String, String> = emptyMap()
)

data class ActionPlan(
    val type: String,
    val payload: Map<String, String> = emptyMap()
)

data class AssistantReply(
    val text: String,
    val shouldSpeak: Boolean = true,
    val actionPlan: ActionPlan? = null
)

data class UserPreference(
    val userId: String,
    val key: String,
    val value: String,
    val updatedAt: Instant = Instant.now()
)

data class InteractionLog(
    val userId: String,
    val text: String,
    val intent: String,
    val response: String,
    val createdAt: Instant = Instant.now()
)

data class CommandResponse(
    val intent: IntentResult,
    val reply: AssistantReply
)

data class AnalyticsSnapshot(
    val totalCommands: Int,
    val topIntents: Map<String, Int>,
    val lastInteractionAt: Instant?
)
