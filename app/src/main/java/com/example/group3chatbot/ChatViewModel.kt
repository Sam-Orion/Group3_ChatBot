package com.example.group3chatbot

import android.graphics.Bitmap
import com.example.group3chatbot.data.Chat
import com.example.group3chatbot.data.ChatData.getResponseWithImage

class ChatViewModel: ViewModel() {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    fun onEvent(event: ChatUIEvent) {
        when (event) {
            is ChatUIEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    addPrompt(event.prompt, event.bitmap)

                    if (event.bitmap != null) {
                        getResponseWithImage(event.prompt, event.bitmap)
                    } else {
                        getResponse(event.prompt)
                    }
                }
            }

            is ChatUIEvent.UpdatePrompt -> {
                _chatState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }
        }
    }

    private fun addPrompt(prompt: String, bitmap: Bitmap?) {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(promt, bitmap, true))
                },
                prompt = "",
                bitmap = null
            )
        }
    }

    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            val chat = ChatDate.getResponse(prompt)
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                )
            }
        }
    }
    private fun getResponseWithImage(prompt: String, bitmap: Bitmap) {
          viewModelScope.launch {
              val chat = ChatDate.getResponseWithImage(prompt, bitmap)
              _chatState.update {
                  it.copy(
                      chatList = it.chatList.toMutableList().apply {
                          add(0, chat)
                      }
                  )
              }
          }
    }
}