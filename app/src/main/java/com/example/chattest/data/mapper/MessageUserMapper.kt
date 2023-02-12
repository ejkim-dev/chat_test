package com.example.chattest.data.mapper

import com.example.chattest.data.entity.MessageUserEntity
import com.example.chattest.domain.model.MessageUser

class MessageUserMapper : BaseMapper<MessageUserEntity, MessageUser> {

    private val userMessageListMapper = UserMessageListMapper()

    override fun mapToModelFrom(entity: MessageUserEntity): MessageUser {
        return MessageUser (
            userMessages = userMessageListMapper.mapToModelFrom(entity.userMessageList)
        )
    }
}