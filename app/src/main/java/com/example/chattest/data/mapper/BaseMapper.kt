package com.example.chattest.data.mapper

interface BaseMapper<Entity, Model> {
    fun mapToModelFrom(entity: Entity): Model
}