package com.example.group3chatbot.data

import android.graphics.Bitmap

object ChatData {

    val api_key = System.getenv("API_KEY")

    suspend fun getResponse(prompt: String): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro", apiKey= api_key
        )

        try {
            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(prompt)
            }

            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: ResponseStoppedException) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }

    }

    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision", apiKey= api_key
        )

        try {

            val inputContent = content {
                image(bitmap)
                text(prompt)
            }

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }

            return Chat(
                prompt = response.text ?: "error",
                bitmap = null,
                isFromUser = false
            )

        } catch (e: ResponseStoppedException) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }

    }

}
