package com.example.chattest.data.entity

import com.google.gson.annotations.SerializedName

data class UserMessageListEntity(
    val company: String,
    @SerializedName("count_unread_chat") val countUnreadChat: Int,
    val id: Int,
    @SerializedName("user_id") val userId: Int,
    val job: String,
    @SerializedName("last_chat_date_time") val lastChatDateTime: String,
    @SerializedName("last_chat_msg") val lastChatMessage: String,
    val name: String,
    @SerializedName("profile_img") val profileImage: String?
)