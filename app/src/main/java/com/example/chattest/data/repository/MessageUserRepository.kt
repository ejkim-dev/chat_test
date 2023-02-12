package com.example.chattest.data.repository

import com.example.chattest.data.entity.MessageUserEntity
import com.example.chattest.data.entity.ResultEntity
import io.reactivex.rxjava3.core.Single

interface MessageUserRepository {
    fun getMessageUsers(): Single<ResultEntity<MessageUserEntity>>
}