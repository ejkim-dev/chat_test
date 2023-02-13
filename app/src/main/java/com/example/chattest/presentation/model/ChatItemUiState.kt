package com.example.chattest.presentation.model

data class ChatItemUiState(
    val index: Int,
    val messageFrom: Int,
    val messageTo: Int,
    val text: String,
    val createdDate: String,
    val unRead: Boolean = true
)
