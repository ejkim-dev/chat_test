package com.example.chattest.domain.model

data class ChatInfo(
    val messageFrom: Int,
    val messageTo: Int,
    val text: String,
    val createdDate: String,
    val unRead: Boolean
)
