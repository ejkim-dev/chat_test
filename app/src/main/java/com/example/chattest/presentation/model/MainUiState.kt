package com.example.chattest.presentation.model

data class MainUiState(
    val status: ViewStatus,
    val roomId: Int? = null
) {
    enum class ViewStatus {
        HOME, MESSAGE, MYPAGE, PEOPLE, RECRUIT, CHAT
    }
}
