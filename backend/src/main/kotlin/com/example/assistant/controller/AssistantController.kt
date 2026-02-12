package com.example.assistant.controller

import com.example.assistant.model.CommandRequest
import com.example.assistant.model.CommandResponse
import com.example.assistant.model.InteractionLog
import com.example.assistant.service.HistoryService
import com.example.assistant.service.IntentService
import com.example.assistant.service.ResponseService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/assistant")
class AssistantController(
    private val intentService: IntentService,
    private val responseService: ResponseService,
    private val historyService: HistoryService
) {

    @PostMapping("/command")
    fun handleCommand(@Valid @RequestBody request: CommandRequest): CommandResponse {
        val intent = intentService.detectIntent(request)
        val reply = responseService.buildReply(request.userId, intent)

        historyService.track(
            InteractionLog(
                userId = request.userId,
                text = request.text,
                intent = intent.intent,
                response = reply.text
            )
        )

        return CommandResponse(intent, reply)
    }
}
