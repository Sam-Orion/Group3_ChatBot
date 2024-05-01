package com.example.group3chatbot.data

import android.graphics.Bitmap

/**
 * @author Shubham Misra
 */
data class Chat (
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean
)