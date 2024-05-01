package com.example.group3chatbot

import android.graphics.Bitmap
import com.example.group3chatbot.data.Chat

/**
 * @author Shubham Misra
 */
data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)