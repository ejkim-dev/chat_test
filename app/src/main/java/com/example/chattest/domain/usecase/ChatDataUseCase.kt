package com.example.chattest.domain.usecase

import com.example.chattest.domain.model.ChatData
import com.example.chattest.domain.model.ResultModel
import io.reactivex.rxjava3.core.Single

interface ChatDataUseCase {
    fun getChatData(roomId: Int) : Single<ResultModel<ChatData>>
}