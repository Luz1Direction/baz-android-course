package com.example.criptomonedasapp.data.network

import com.example.criptomonedasapp.data.network.model.network.*

interface CryptocurrenciesRemoteDataSource {

    suspend fun getCoinDetails(coin: String): CoinDetailModel?
    suspend fun getAsksAndBids(coin: String): AsksAndBidsModel?
}