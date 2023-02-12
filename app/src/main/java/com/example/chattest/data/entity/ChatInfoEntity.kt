package com.example.chattest.data.entity

data class ChatInfoEntity(
    val messageFrom: Int,
    val messageTo: Int,
    val text: String,
    val createdDate: String,
    val unRead: Boolean
)
