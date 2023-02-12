package com.example.chattest.domain.usecase

import com.example.chattest.data.entity.ResultEntity
import com.example.chattest.data.mapper.BaseMapper
import com.example.chattest.domain.model.ResultModel

interface BaseUseCase<Entity, Model> {
    fun makeDataOrErrorModel(resultEntity: ResultEntity<Entity>, mapper: BaseMapper<Entity, Model>): ResultModel<Model> {
        return when(resultEntity){
            is ResultEntity.Data<Entity> -> {
                val model = mapper.mapToModelFrom(resultEntity.data)
                ResultModel.Data(model)
            }
            is ResultEntity.Error -> ResultModel.Error(resultEntity.throwable)
        }
    }
}