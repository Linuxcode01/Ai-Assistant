package com.example.assistant.service

import com.example.assistant.model.AnalyticsSnapshot
import com.example.assistant.model.InteractionLog
import com.example.assistant.repository.InteractionStore
import org.springframework.stereotype.Service

@Service
class HistoryService(
    private val interactionStore: InteractionStore
) {

    fun track(log: InteractionLog) {
        interactionStore.add(log)
    }

    fun history(userId: String): List<InteractionLog> = interactionStore.byUser(userId)

    fun analytics(): AnalyticsSnapshot {
        val allLogs = interactionStore.all()
        val topIntents = allLogs.groupingBy { it.intent }.eachCount().toList()
            .sortedByDescending { it.second }
            .take(5)
            .toMap()

        return AnalyticsSnapshot(
            totalCommands = allLogs.size,
            topIntents = topIntents,
            lastInteractionAt = allLogs.maxByOrNull { it.createdAt }?.createdAt
        )
    }
}
