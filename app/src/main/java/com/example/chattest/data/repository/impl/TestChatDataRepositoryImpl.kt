package com.example.chattest.data.repository.impl

import com.example.chattest.data.entity.ChatDataEntity
import com.example.chattest.data.entity.ResultEntity
import com.example.chattest.data.repository.ChatDataRepository
import io.reactivex.rxjava3.core.Single

class TestChatDataRepositoryImpl: ChatDataRepository {
    override fun getChatData(roomId: Int): Single<ResultEntity<ChatDataEntity>> {
        return when (roomId) {
            1 -> Single.just(ResultEntity.Data(ChatDataEntity.test1()))
            2 -> Single.just(ResultEntity.Data(ChatDataEntity.test2()))
            3 -> Single.just(ResultEntity.Data(ChatDataEntity.test3()))
            else -> Single.just(ResultEntity.Data(ChatDataEntity(roomId)))
        }
    }
}