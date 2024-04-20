package com.example.group3chatbot

import android.graphics.Bitmap

data class ChatState {
    val chatlist: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
}