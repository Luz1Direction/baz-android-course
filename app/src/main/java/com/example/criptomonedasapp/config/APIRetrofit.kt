package com.example.criptomonedasapp.config

import com.example.criptomonedasapp.services.APIService
import com.example.criptomonedasapp.services.CryptoEndPoints.URL_BASE_
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIRetrofit {

   private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val request = chain.request()
            val builder = request
                .newBuilder()
                .header("User-Agent", request.url.host)
            val mutatedRequest = builder.build()
            val response = chain.proceed(mutatedRequest)
            response
        }
        addInterceptor(interceptor)
    }.build()

    fun getConfigRetrofit(): APIService {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(URL_BASE_)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(APIService::class.java)
    }

}