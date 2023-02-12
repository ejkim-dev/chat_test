package com.example.chattest.domain.usecase

import com.example.chattest.data.entity.ResultEntity
import io.reactivex.rxjava3.core.Single

interface TokenUseCase {
    fun loadHasUserToken() : Single<ResultEntity<Boolean>>
    fun saveHasUserToken(hasUserToken: Boolean)
}