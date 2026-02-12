package com.example.phoneassistant.controls

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri

class PhoneControlExecutor(
    private val context: Context,
    private val audioManager: AudioManager
) {

    fun execute(actionType: String, payload: Map<String, String>) {
        when (actionType) {
            "PHONE_CALL" -> startCall(payload["contact"])
            "VOLUME" -> setVolume(payload["level"])
            "OPEN_APP" -> openApp(payload["appName"])
            "FLASHLIGHT" -> {
                // TODO: integrate CameraManager torch mode handling.
            }
        }
    }

    private fun startCall(contact: String?) {
        if (contact.isNullOrBlank()) return
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$contact")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    private fun setVolume(level: String?) {
        val parsed = level?.toIntOrNull()?.coerceIn(0, 100) ?: return
        val max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val target = (max * parsed) / 100
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, target, 0)
    }

    private fun openApp(appName: String?) {
        if (appName.isNullOrBlank()) return
        val launchIntent = context.packageManager.getLaunchIntentForPackage(appName)
        launchIntent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (launchIntent != null) {
            context.startActivity(launchIntent)
        }
    }
}
