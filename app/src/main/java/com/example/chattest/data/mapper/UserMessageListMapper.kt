package com.example.chattest.data.mapper

import com.example.chattest.data.entity.UserMessageListEntity
import com.example.chattest.domain.model.MessageUserInfo

class UserMessageListMapper : BaseMapper<List<UserMessageListEntity>, List<MessageUserInfo>> {

    override fun mapToModelFrom(entity: List<UserMessageListEntity>): List<MessageUserInfo> {
        return entity.map {
            MessageUserInfo(
                company = it.company,
                countUnreadChat = it.countUnreadChat,
                id = it.id,
                job = it.job,
                lastChatMessage = it.lastChatMessage,
                lastChatDateTime = it.lastChatDateTime,
                name = it.name,
                profileImage = it.profileImage
            )
        }
    }
}