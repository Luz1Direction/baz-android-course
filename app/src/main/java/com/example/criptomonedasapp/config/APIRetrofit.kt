package com.example.criptomonedasapp.config

import com.example.criptomonedasapp.services.APIService
import com.example.criptomonedasapp.services.CryptoEndPoints.URL_BASE_
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRetrofit {

    

    fun getConfigRetrofit(): APIService {
        var retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE_)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(APIService::class.java)
    }


}