package com.example.chattest.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chattest.domain.model.ChatData
import com.example.chattest.domain.model.MessageUser
import com.example.chattest.domain.usecase.ChatDataUseCase
import com.example.chattest.domain.usecase.MessageUserUseCase
import com.example.chattest.presentation.model.ChatItemUiState
import com.example.chattest.presentation.model.ChatUserItemUiState
import com.example.chattest.presentation.model.MainUiState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel(
    application: Application,
    private val messageUserUseCase: MessageUserUseCase,
    private val chatDataUseCase: ChatDataUseCase
) : BaseViewModel(application) {
    private val _messageUser = MutableLiveData<MessageUser>()
    val messageUser: LiveData<MessageUser> = _messageUser

    private val _chatData = MutableLiveData<ChatData>()
    val chatData: LiveData<ChatData> = _chatData

    private val _chatItemUiState = MutableLiveData<List<ChatItemUiState>?>()
    val chatItemUiState: LiveData<List<ChatItemUiState>?> = _chatItemUiState

    private val _currentMessageUser = MutableLiveData<ChatUserItemUiState>()
    val currentMessageUser: LiveData<ChatUserItemUiState> = _currentMessageUser

    private val _mainUiState : BehaviorSubject<MainUiState> = BehaviorSubject.createDefault(MainUiState(MainUiState.ViewStatus.MESSAGE))
    val mainUiState: Observable<MainUiState> = _mainUiState

    fun getMessageUsers() {
        getApiResult(
            messageUserUseCase.getMessageUsers(),
            success = {
                _messageUser.postValue(it)
            },
            true,
            "Message Users"
        )
    }

    fun getChatMessage(roomId: Int) {
        Log.d("####", "getChatMessage: $roomId")
        getApiResult(
            chatDataUseCase.getChatData(roomId = roomId),
            success = {
                _chatData.postValue(it)
            },
            true,
            "Chat Room"
        )
    }

    fun setChatItemUiState (chatItemUiState: List<ChatItemUiState>?) {
        _chatItemUiState.postValue(chatItemUiState)
    }

    fun updateChatItemUiState (chatItemUiState: ChatItemUiState) {
        Log.d("####", "updateChatItemUiState: $chatItemUiState | ${this.chatItemUiState.value == null}")
        if (this.chatItemUiState.value == null) {
            _chatItemUiState.postValue(listOf(chatItemUiState))
        } else {
            val newChatItemUiStates = this.chatItemUiState.value!!.toMutableList()
            newChatItemUiStates.add(chatItemUiState)
            _chatItemUiState.postValue(newChatItemUiStates)
        }
    }

    fun moveChatRoom(chatUserItemUiState: ChatUserItemUiState) {
        _mainUiState.onNext(MainUiState(MainUiState.ViewStatus.CHAT))
        _currentMessageUser.postValue(chatUserItemUiState)
    }

    fun moveHome() {
        _mainUiState.onNext(MainUiState(MainUiState.ViewStatus.HOME))
    }

    fun moveMessage() {
        _mainUiState.onNext(MainUiState(MainUiState.ViewStatus.MESSAGE))
    }

    fun moveMyPage() {
        _mainUiState.onNext(MainUiState(MainUiState.ViewStatus.MYPAGE))
    }

    fun movePeople() {
        _mainUiState.onNext(MainUiState(MainUiState.ViewStatus.PEOPLE))
    }

    fun moveRecruit() {
        _mainUiState.onNext(MainUiState(MainUiState.ViewStatus.RECRUIT))
    }
}