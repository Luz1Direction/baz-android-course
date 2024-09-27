package com.example.criptomonedasapp.data.local

import com.example.criptomonedasapp.data.network.model.CoinCardModel
import com.example.criptomonedasapp.data.local.database.entities.AsksEntity
import com.example.criptomonedasapp.data.local.database.entities.BidsEntity
import com.example.criptomonedasapp.data.local.database.entities.CoinCardEntity
import com.example.criptomonedasapp.data.local.database.entities.CoinDetailEntity

interface CryptocurrenciesLocalDataSource {
    suspend fun insertAllCoin(coins: List<CoinCardEntity>)
    suspend fun getCoinList(): List<CoinCardEntity>
    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity)
    suspend fun getCoinDetail(coin: String): CoinDetailEntity?
    suspend fun insertAsks(coins: List<AsksEntity>)
    suspend fun getAsks(coin: String): List<AsksEntity>
    suspend fun insertBids(coins: List<BidsEntity>)
    suspend fun getBids(coin: String): List<BidsEntity>
    suspend fun deleteAllAsks(coin: String)
    suspend fun deleteAllBids(coin: String)
}