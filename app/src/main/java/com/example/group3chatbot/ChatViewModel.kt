package com.example.group3chatbot

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import androidx.core.graphics.Insets
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.group3chatbot.data.Chat
import com.example.group3chatbot.data.ChatData
import com.example.group3chatbot.data.ChatData.getResponseWithImage
import com.example.group3chatbot.data.ChatData.getResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel() : ViewModel(), Parcelable {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    constructor(parcel: Parcel) : this() {
    }

    suspend fun onEvent(event: ChatUIEvent) {
        when (event) {
            is ChatUIEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    event.bitmap?.let { addPrompt(event.prompt, it) }

                    if (event.bitmap != null) {
                        ChatData.getResponseWithImage(event.prompt, event.bitmap)
                    } else {
                        ChatData.getResponseWithImage(event.prompt, event.bitmap)
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

    private fun addPrompt(prompt: String, bitmap: Bitmap) {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(prompt, bitmap, true))
                },
                prompt = "",
                bitmap = null
            )
        }
    }

    private fun add(i: Int, chat: Chat): Insets {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChatViewModel> {
        override fun createFromParcel(parcel: Parcel): ChatViewModel {
            return ChatViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ChatViewModel?> {
            return arrayOfNulls(size)
        }
    }

    fun getResponse(prompt: String, ChatDate: Any) {
        viewModelScope.launch {
            val chat = ChatDate.getResponse(prompt)
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                    }
                )
            }
        }
    }
    fun getResponseWithImage(prompt: String, bitmap: Bitmap, ChatDate: Any) {
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