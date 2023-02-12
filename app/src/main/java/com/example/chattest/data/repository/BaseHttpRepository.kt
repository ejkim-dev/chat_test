package com.example.chattest.data.repository

import com.example.chattest.data.entity.ResultEntity
import retrofit2.Response

interface BaseHttpRepository<Entity> {
    fun makeDataOrErrorResult(response: Response<Entity>): ResultEntity<Entity> {
        return with (response) {
            if (isSuccessful && body() != null) {
                ResultEntity.Data<Entity>(body()!!)
            } else if (isSuccessful && body() == null) {
                ResultEntity.Error(Throwable("Response body is null"))
            } else {
                ResultEntity.Error(Throwable(response.errorBody()?.string()))
            }
        }
    }
}