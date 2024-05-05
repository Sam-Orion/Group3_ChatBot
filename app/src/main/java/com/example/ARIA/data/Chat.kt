package com.example.ARIA.data

import android.graphics.Bitmap

/**
 * @author Shubham Misra
 */
data class Chat (
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean
)