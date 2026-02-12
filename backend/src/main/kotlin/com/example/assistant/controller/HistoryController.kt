package com.example.assistant.controller

import com.example.assistant.model.AnalyticsSnapshot
import com.example.assistant.model.InteractionLog
import com.example.assistant.service.HistoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/history")
class HistoryController(
    private val historyService: HistoryService
) {

    @GetMapping("/{userId}")
    fun getUserHistory(@PathVariable userId: String): List<InteractionLog> {
        return historyService.history(userId)
    }

    @GetMapping("/analytics/overview")
    fun getOverview(): AnalyticsSnapshot {
        return historyService.analytics()
    }
}
