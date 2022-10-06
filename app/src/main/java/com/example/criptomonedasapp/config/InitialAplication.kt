package com.example.criptomonedasapp.config

import android.app.Application
import androidx.room.Room
import com.example.criptomonedasapp.mvvm.data.database.CoinsDatabase
import com.example.criptomonedasapp.services.APIService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InitialAplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}