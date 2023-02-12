package com.example.chattest.data.repository.impl

import com.example.chattest.data.constant.ApiContainer
import com.example.chattest.data.datasource.HttpDatasource
import com.example.chattest.data.entity.MessageUserEntity
import com.example.chattest.data.entity.ResultEntity
import com.example.chattest.data.repository.BaseHttpRepository
import com.example.chattest.data.repository.MessageUserRepository
import io.reactivex.rxjava3.core.Single

class MessageUserRepositoryImpl : MessageUserRepository, BaseHttpRepository<MessageUserEntity> {
    private val httpDatasource: HttpDatasource
        get() = HttpDatasource(ApiContainer.BASE_URL)

    override fun getMessageUsers(): Single<ResultEntity<MessageUserEntity>> {
        return httpDatasource.api.getUserMessages()
            .map { makeDataOrErrorResult(it) }
    }
}