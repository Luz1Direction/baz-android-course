package com.example.criptomonedasapp.domain.repository

import com.example.criptomonedasapp.domain.model.*


interface CryptocurrenciesRepository{
    suspend  fun getAllCoins(coins: List<CoinCardData>): List<CoinCardData>
    suspend fun getCoinDetails(coin: String): CoinDetailModelData?
    suspend fun getAsks(coin: String): List<AsksModelData>
    suspend fun getBids(coin: String): List<BidsModelData>
}