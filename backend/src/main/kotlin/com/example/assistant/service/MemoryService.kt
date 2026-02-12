package com.example.assistant.service

import com.example.assistant.model.UserPreference
import com.example.assistant.repository.PreferenceStore
import org.springframework.stereotype.Service

@Service
class MemoryService(
    private val preferenceStore: PreferenceStore
) {

    fun savePreference(preference: UserPreference): UserPreference = preferenceStore.upsert(preference)

    fun listPreferences(userId: String): List<UserPreference> = preferenceStore.findByUser(userId)

    fun getPreferredLanguage(userId: String): String = preferenceStore.findValue(userId, "language") ?: "hi-IN"
}
