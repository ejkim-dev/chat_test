package com.example.chattest.domain.model

data class MessageUserInfo(
    val company: String,
    val countUnreadChat: Int,
    val id: Int,
    val userId: Int,
    val job: String,
    val lastChatDateTime: String,
    val lastChatMessage: String,
    val name: String,
    val profileImage: String?
)