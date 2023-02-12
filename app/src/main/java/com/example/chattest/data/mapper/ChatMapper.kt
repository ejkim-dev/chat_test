package com.example.chattest.data.mapper

import com.example.chattest.data.entity.ChatDataEntity
import com.example.chattest.domain.model.ChatData

class ChatMapper: BaseMapper<ChatDataEntity, ChatData> {
    private val chatInfoListMapper = ChatInfoListMapper()

    override fun mapToModelFrom(entity: ChatDataEntity): ChatData {
        return ChatData(
            roomId = entity.roomId,
            chatInfoList = entity.chatInfoList?.let { chatInfoListMapper.mapToModelFrom(it) }
        )
    }
}