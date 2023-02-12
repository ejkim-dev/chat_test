package com.example.chattest.data.repository.impl

import com.example.chattest.data.constant.SharedPreferenceConst
import com.example.chattest.data.datasource.SharedPreferenceDataSource
import com.example.chattest.data.entity.ResultEntity
import com.example.chattest.data.repository.BaseSharedPreferenceRepository
import com.example.chattest.data.repository.TokenRepository
import io.reactivex.rxjava3.core.Single

class TokenRepositoryImpl(
    private val sharedPreferenceDataSource: SharedPreferenceDataSource
) : TokenRepository, BaseSharedPreferenceRepository<Boolean> {
    override fun loadHasUserToken(): Single<ResultEntity<Boolean>> {
        return sharedPreferenceDataSource.loadBooleanRx(SharedPreferenceConst.USER_TOKEN, false)
            .map { makeDataOrErrorResult(it) }
    }

    override fun saveHasUserToken(hasUserToken: Boolean) {
        return sharedPreferenceDataSource.saveBoolean(SharedPreferenceConst.USER_TOKEN, hasUserToken)
    }
}