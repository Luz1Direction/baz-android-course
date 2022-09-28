package com.example.criptomonedasapp.config

import android.app.Application
import androidx.room.Room
import com.example.criptomonedasapp.mvvm.data.database.CoinsDatabase
import com.example.criptomonedasapp.services.APIService

class InitialAplication: Application() {

    companion object {
        lateinit var webService: APIService
        lateinit var CryptocurrenciesBD: CoinsDatabase
    }

    override fun onCreate() {
        super.onCreate()
        webService = APIRetrofit().getConfigRetrofit()
        CryptocurrenciesBD = Room.databaseBuilder(
            this,
            CoinsDatabase::class.java,
            "Cryptocurrencies"
        ).build()
    }
}