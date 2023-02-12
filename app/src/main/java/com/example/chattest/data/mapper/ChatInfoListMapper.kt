package com.example.chattest.data.mapper

import com.example.chattest.data.entity.ChatInfoEntity
import com.example.chattest.domain.model.ChatInfo

class ChatInfoListMapper: BaseMapper<List<ChatInfoEntity>, List<ChatInfo>> {
    override fun mapToModelFrom(entity: List<ChatInfoEntity>): List<ChatInfo> {
        return entity.map {
            ChatInfo(
                messageFrom = it.messageFrom,
                messageTo = it.messageTo,
                text = it.text,
                createdDate = it.createdDate,
                unRead = it.unRead
            )
        }
    }
}