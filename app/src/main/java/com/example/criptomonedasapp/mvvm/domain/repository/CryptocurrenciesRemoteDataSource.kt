package com.example.criptomonedasapp.mvvm.domain.repository

import com.example.criptomonedasapp.model.network.*
import javax.inject.Inject

interface CryptocurrenciesRemoteDataSource {
    suspend fun getCoinDetails(coin: String): CoinDetailModel?
    suspend fun getAsksAndBids(coin: String): AsksAndBidsModel?
}