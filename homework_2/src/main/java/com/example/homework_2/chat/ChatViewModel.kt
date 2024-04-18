package com.example.homework_2.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_2.network.NarrowItem
import com.example.homework_2.network.RetrofitModule
import com.example.homework_2.utils.runCatchingNonCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ChatViewModel : ViewModel() {
    private val _messagesState = MutableStateFlow<ChatState>(ChatState.Init)
    val messagesState: SharedFlow<ChatState> get() = _messagesState
    private var previousResponse: List<MessageItem>? = null
    private val pollingIntervalMillis = 2000L
    private val timeoutMillis = 600000L
    private var currentId = ""
    private var lastEventId = 0
    private var allMessages: MutableList<MessageItem> = mutableListOf()
    private var isPollingActive = false

    init {
        registerEvent()
    }

    private fun startPolling() {
        if (!isPollingActive) {
            isPollingActive = true
            viewModelScope.launch {
                while (currentId.isNotEmpty() && lastEventId != 0) {
                    trackEvent()
                    delay(pollingIntervalMillis)
                }
                isPollingActive = false
            }
        }
    }

    fun getMessages(topicName: String, streamName: String) {
        viewModelScope.launch {
            runCatchingNonCancellation {
                val narrow = listOf(
                    NarrowItem(topicName, "stream"),
                    NarrowItem(streamName, "topic")
                )
                val jsonString = Json.encodeToString(narrow)
                val response = RetrofitModule.create.getTopicMessages(
                    narrow = jsonString
                )
                allMessages = response.messages.toMutableList()
                if (response.messages != previousResponse) {
                    if (response.messages != previousResponse) {
                        _messagesState.emit(ChatState.Success(response.messages))
                    }
                }
                previousResponse = response.messages
            }.onFailure {
                _messagesState.emit(ChatState.Error(it.message.toString() + "getMessages"))
                it.printStackTrace()
            }
        }
    }

    private fun registerEvent() {
        viewModelScope.launch {
            runCatchingNonCancellation {
                val response = RetrofitModule.create.registerEvent(
                    timeoutMillis.toInt(),
                    Json.encodeToString(listOf("message", "realm_message")),
                    Json.encodeToString(listOf("message"))
                )
                currentId = response.id
                lastEventId = response.lastEventId
                startPolling()
            }.onFailure {
                _messagesState.emit(ChatState.Error(it.message.toString() + "registerEvent"))
            }
        }
    }

    private fun trackEvent() {
        viewModelScope.launch {
            runCatchingNonCancellation {
                val response = RetrofitModule.create.trackEvent(currentId, lastEventId)
                if (previousResponse != null) {
                    for (event in response.events) {
                        if (event.type == MESSAGE) {
                            if (previousResponse?.any { it.id == event.message?.id } == false) {
                                allMessages.add(event.message!!)
                                _messagesState.emit(ChatState.Success(allMessages))
                            }
                        }
                    }
                    previousResponse = allMessages
                }
            }.onFailure {
                _messagesState.emit(ChatState.Error(it.message.toString() + "trackEvent"))
                it.printStackTrace()
            }
        }
    }

    fun sendMessage(streamName: String, topicName: String, content: String) {
        viewModelScope.launch {
            runCatchingNonCancellation {
                RetrofitModule.create.sendMessage(
                    stream = streamName,
                    topic = topicName,
                    message = content
                )
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun sendReaction(emojiName: String, messageId: Int){
        viewModelScope.launch {
            runCatchingNonCancellation {
                RetrofitModule.create.addEmoji(messageId, emojiName)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun deleteReaction(emojiName: String, messageId: Int){
        viewModelScope.launch {
            runCatchingNonCancellation {
                RetrofitModule.create.deleteEmoji(messageId, emojiName)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    private companion object {
        private const val MESSAGE = "message"
    }
}