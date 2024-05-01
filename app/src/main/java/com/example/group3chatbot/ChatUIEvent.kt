package com.example.group3chatbot

import android.graphics.Bitmap

/**
 * @author Shubham Misra
 */
sealed class ChatUIEvent {
    data class UpdatePrompt(val newPrompt: String): ChatUIEvent()
    data class SendPrompt(
        val prompt: String,
        val bitmap: Bitmap?
    ) : ChatUIEvent()
}