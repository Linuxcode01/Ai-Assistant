package com.example.assistant.controller

import com.example.assistant.model.UserPreference
import com.example.assistant.service.MemoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/memory")
class MemoryController(
    private val memoryService: MemoryService
) {

    @PostMapping("/preferences")
    fun savePreference(@RequestBody request: UserPreference): UserPreference {
        return memoryService.savePreference(request)
    }

    @GetMapping("/preferences/{userId}")
    fun getPreferences(@PathVariable userId: String): List<UserPreference> {
        return memoryService.listPreferences(userId)
    }
}
