package com.example.chattest.data.repository

import com.example.chattest.data.entity.ResultEntity
import io.reactivex.rxjava3.core.Single

interface TokenRepository {
    fun loadHasUserToken() : Single<ResultEntity<Boolean>>
    fun saveHasUserToken(hasUserToken: Boolean)
}