package com.example.chattest.presentation.model

data class MainUiState(
    val status: ViewStatus,
) {
    enum class ViewStatus {
        HOME, MESSAGE, MYPAGE, PEOPLE, RECRUIT, CHAT
    }
}
