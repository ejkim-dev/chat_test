package com.example.chattest.presentation.model

data class ChatUserItemUiState(
    val id: Int,
    val userId: Int,
    val imageUrl: String?,
    val name: String,
    val date: String,
    val job: String,
    val company: String,
    val message: String,
    val messageCount: Int
)
