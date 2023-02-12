package com.example.chattest.data.entity

sealed class ResultEntity<out DataType> {
    data class Data<DataType>(var data : DataType) : ResultEntity<DataType>()
    data class Error(val throwable: Throwable) : ResultEntity<Nothing>()
}