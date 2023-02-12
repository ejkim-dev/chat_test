package com.example.chattest.domain.usecase

import com.example.chattest.domain.model.MessageUser
import com.example.chattest.domain.model.ResultModel
import io.reactivex.rxjava3.core.Single

interface MessageUserUseCase {
    fun getMessageUsers() : Single<ResultModel<MessageUser>>
}