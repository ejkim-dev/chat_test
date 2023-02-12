package com.example.chattest.data.repository

import com.example.chattest.data.entity.ResultEntity

interface BaseSharedPreferenceRepository<Type> {
	fun makeDataOrErrorResult(value: Type): ResultEntity<Type> {
		return ResultEntity.Data(value)
	}

	fun makeAnyOrErrorResult(value: Any): ResultEntity<Any> {
		return ResultEntity.Data(value)
	}
}