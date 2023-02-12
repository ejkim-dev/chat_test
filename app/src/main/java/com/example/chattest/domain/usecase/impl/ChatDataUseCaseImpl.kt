package com.example.chattest.domain.usecase.impl

import com.example.chattest.data.entity.ChatDataEntity
import com.example.chattest.data.mapper.ChatMapper
import com.example.chattest.data.repository.ChatDataRepository
import com.example.chattest.domain.model.ChatData
import com.example.chattest.domain.model.ResultModel
import com.example.chattest.domain.usecase.BaseUseCase
import com.example.chattest.domain.usecase.ChatDataUseCase
import io.reactivex.rxjava3.core.Single

class ChatDataUseCaseImpl(
    private val chatDataRepository: ChatDataRepository
) : ChatDataUseCase, BaseUseCase<ChatDataEntity, ChatData> {

    private val chatMapper = ChatMapper()

    override fun getChatData(roomId: Int): Single<ResultModel<ChatData>> {
        return chatDataRepository.getChatData(roomId)
            .map { makeDataOrErrorModel(it, chatMapper) }
    }
}