package com.example.chattest.data.repository

import com.example.chattest.data.entity.ChatDataEntity
import com.example.chattest.data.entity.ResultEntity
import io.reactivex.rxjava3.core.Single

interface ChatDataRepository {
    fun getChatData(roomId: Int) : Single<ResultEntity<ChatDataEntity>>
}