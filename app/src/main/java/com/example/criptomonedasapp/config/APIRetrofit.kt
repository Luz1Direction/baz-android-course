package com.example.criptomonedasapp.config

import com.example.criptomonedasapp.services.APIService
import com.example.criptomonedasapp.services.CryptoEndPoints.URL_BASE_
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRetrofit {

    fun getConfigRetrofit(): APIService {
        var mRetrofit = Retrofit.Builder()
            .baseUrl(URL_BASE_)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return mRetrofit.create(APIService::class.java)
    }
}