package com.example.chattest.domain.model

data class UserMessageList(
    val company: String,
    val countUnreadChat: Int,
    val id: Int,
    val job: String,
    val lastChatDateTime: String,
    val lastChatMessage: String,
    val name: String,
    val profileImage: String?
)