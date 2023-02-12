package com.example.chattest.data.mapper

import com.example.chattest.data.entity.UserMessageListEntity
import com.example.chattest.domain.model.UserMessageList

class UserMessageListMapper : BaseMapper<List<UserMessageListEntity>, List<UserMessageList>> {

    override fun mapToModelFrom(entity: List<UserMessageListEntity>): List<UserMessageList> {
        return entity.map {
            UserMessageList(
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