package com.example.phoneassistant.nlp

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class AssistantApiClient(
    private val baseUrl: String,
    private val httpClient: OkHttpClient = OkHttpClient()
) {

    fun sendCommand(userId: String, text: String): String {
        val body = """
            {
              "userId":"$userId",
              "text":"$text"
            }
        """.trimIndent()

        val request = Request.Builder()
            .url("$baseUrl/api/v1/assistant/command")
            .post(body.toRequestBody("application/json".toMediaType()))
            .build()

        httpClient.newCall(request).execute().use { response ->
            return response.body?.string().orEmpty()
        }
    }
}
