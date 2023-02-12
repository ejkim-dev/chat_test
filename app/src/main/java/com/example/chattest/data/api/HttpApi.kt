package com.example.chattest.data.api

import com.example.chattest.data.entity.MessageUserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface HttpApi {
    @GET("ejkim-dev/f9e280834a86a7d57a0ff149daea0ed1/raw/5e1f89730e968e356ca6fbf17dc8c157f3dbd971")
    fun getUserMessages(): Single<Response<MessageUserEntity>>
}