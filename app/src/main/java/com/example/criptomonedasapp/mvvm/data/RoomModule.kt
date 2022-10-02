package com.example.criptomonedasapp.mvvm.data

import android.content.Context
import androidx.room.Room
import com.example.criptomonedasapp.mvvm.data.database.CoinsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, CoinsDatabase::class.java, "Cryptocurrencies"
    ).build()

    @Singleton
    @Provides
    fun provideCoinsDao(db:CoinsDatabase) = db.cryptocurrenciesDao()


}