package com.example.chattest.domain.usecase.impl

import com.example.chattest.data.entity.MessageUserEntity
import com.example.chattest.data.mapper.MessageUserMapper
import com.example.chattest.data.repository.MessageUserRepository
import com.example.chattest.domain.model.MessageUser
import com.example.chattest.domain.model.ResultModel
import com.example.chattest.domain.usecase.BaseUseCase
import com.example.chattest.domain.usecase.MessageUserUseCase
import io.reactivex.rxjava3.core.Single

class MessageUserUseCaseImpl(
    private val messageUserRepository: MessageUserRepository
) : MessageUserUseCase, BaseUseCase<MessageUserEntity, MessageUser> {

    private val messageUserMapper = MessageUserMapper()

    override fun getMessageUsers(): Single<ResultModel<MessageUser>> {
        return messageUserRepository.getMessageUsers()
            .map { makeDataOrErrorModel(it, messageUserMapper) }
    }
}