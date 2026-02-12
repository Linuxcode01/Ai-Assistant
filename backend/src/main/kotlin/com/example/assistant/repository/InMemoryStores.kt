package com.example.assistant.repository

import com.example.assistant.model.InteractionLog
import com.example.assistant.model.UserPreference
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

@Component
class PreferenceStore {
    private val preferences = ConcurrentHashMap<String, MutableMap<String, UserPreference>>()

    fun upsert(preference: UserPreference): UserPreference {
        val userPreferences = preferences.computeIfAbsent(preference.userId) { ConcurrentHashMap() }
        userPreferences[preference.key] = preference
        return preference
    }

    fun findByUser(userId: String): List<UserPreference> {
        return preferences[userId]?.values?.toList().orEmpty()
    }

    fun findValue(userId: String, key: String): String? {
        return preferences[userId]?.get(key)?.value
    }
}

@Component
class InteractionStore {
    private val logs = CopyOnWriteArrayList<InteractionLog>()

    fun add(log: InteractionLog) {
        logs += log
    }

    fun all(): List<InteractionLog> = logs.toList()

    fun byUser(userId: String): List<InteractionLog> = logs.filter { it.userId == userId }
}
