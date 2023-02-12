package com.example.chattest.domain.model

sealed class ResultModel<out DataType> {
    data class Data<DataType>(var data : DataType) : ResultModel<DataType>()
    data class Error(val throwable: Throwable) : ResultModel<Nothing>()
}