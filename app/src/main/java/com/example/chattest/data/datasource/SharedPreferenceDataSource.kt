package com.example.chattest.data.datasource

import android.content.Context
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class SharedPreferenceDataSource(context: Context, name: String) {
    private val preference by lazy { context.getSharedPreferences(name, Context.MODE_PRIVATE) }

    fun loadBooleanRx(key: String, default: Boolean): Single<Boolean> {
        return Single.just(preference.getBoolean(key, default))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    fun saveBoolean(key: String, value: Boolean) {
        with (preference.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    fun remove(key: String) {
        with (preference.edit()) {
            remove(key)
            apply()
        }
    }
}