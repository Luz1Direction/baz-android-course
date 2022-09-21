package com.example.criptomonedasapp.config

import android.app.Application
import com.example.criptomonedasapp.config.APIRetrofit
import com.example.criptomonedasapp.services.APIService

class InitialAplication: Application() {

    companion object {
        lateinit var webService: APIService
    }

    override fun onCreate() {
        super.onCreate()
        webService = APIRetrofit().getConfigRetrofit()
    }
}