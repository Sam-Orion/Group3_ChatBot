package com.example.ARIA

import android.graphics.Bitmap
import com.example.ARIA.data.Chat

/**
 * @author Shubham Misra
 */
data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)