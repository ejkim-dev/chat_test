package com.example.chattest.data.datasource

import com.example.chattest.data.api.HttpApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpDatasource(private val targetUrl: String) {
    val api: HttpApi
    init {
        api = createApiWithHeader(targetUrl)
    }

    private fun createApiWithHeader(targetUrl: String): HttpApi {
        val resultUrl = if (targetUrl.last() != '/') "$targetUrl/" else targetUrl

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
        val apiRetrofit = Retrofit.Builder()
            .baseUrl(resultUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return apiRetrofit.create(HttpApi::class.java)
    }
}