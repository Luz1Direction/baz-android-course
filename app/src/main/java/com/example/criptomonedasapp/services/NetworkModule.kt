package com.example.criptomonedasapp.services

import com.example.criptomonedasapp.mvvm.data.database.CoinsDatabase
import com.example.criptomonedasapp.mvvm.data.database.domain.repository.CryptocurrenciesDataSource
import com.example.criptomonedasapp.mvvm.data.database.repository.CryptocurrenciesDataSourceImpl
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesRemoteDataSourceImpl
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun cryptocurrenciesRemoteDataSource(): CryptocurrenciesRemoteDataSource {
        return CryptocurrenciesRemoteDataSourceImpl()
    }

    @Singleton
    @Provides
    fun cryptocurrenciesDataSource(
        data: CoinsDatabase
    ): CryptocurrenciesDataSource {
        return CryptocurrenciesDataSourceImpl(data)
    }

}