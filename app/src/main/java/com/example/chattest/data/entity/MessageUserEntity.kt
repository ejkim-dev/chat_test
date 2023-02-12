package com.example.chattest.data.entity

import com.google.gson.annotations.SerializedName

data class MessageUserEntity(
    @SerializedName("user_message_list") val userMessageList: List<UserMessageListEntity>
)