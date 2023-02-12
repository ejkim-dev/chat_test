package com.example.chattest.domain.model


data class ChatData(
    val roomId: Int,
    val chatInfoList: List<ChatInfo>?
)
