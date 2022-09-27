package com.example.criptomonedasapp.mvvm.domain.repository

import com.example.criptomonedasapp.model.network.*

interface CryptocurrenciesRepository {
    suspend fun getCoinList(): List<CoinListModel>
    suspend fun getCoinDetails(coin: String): CoinDetailModel
    suspend fun getAsksAndBids(coin: String): AsksAndBidsModel
}