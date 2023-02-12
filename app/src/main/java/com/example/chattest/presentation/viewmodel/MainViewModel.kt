package com.example.chattest.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chattest.domain.model.MessageUser
import com.example.chattest.domain.usecase.MessageUserUseCase
import com.example.chattest.presentation.model.MainUiState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class MainViewModel(
    application: Application,
    private val messageUserUseCase: MessageUserUseCase
) : BaseViewModel(application) {
    private val _messageUser = MutableLiveData<MessageUser>()
    val messageUser: LiveData<MessageUser> = _messageUser

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

    fun moveChatRoom(id: Int) {
        _mainUiState.onNext(MainUiState(MainUiState.ViewStatus.CHAT, id))
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