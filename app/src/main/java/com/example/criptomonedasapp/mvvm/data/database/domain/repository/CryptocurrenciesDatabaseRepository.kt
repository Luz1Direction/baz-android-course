package com.example.criptomonedasapp.mvvm.data.database.domain.repository

import com.example.criptomonedasapp.model.CoinsModelCard
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity

interface CryptocurrenciesDatabaseRepository {
    suspend fun insertAllCoin(coins: CoinCardEntity)
    suspend fun getCoinList(): ArrayList<CoinsModelCard>
    suspend fun insertCoinDetail(coinDetail: CoinDetailModel)
    suspend fun getCoinDetail(coin: String): CoinDetailModel
    suspend fun insertAsks(coins: List<AsksModel>)
    suspend fun getAsks(coin: String): ArrayList<AsksModel>
    suspend fun insertBids(coins: List<BidsModel>)
    suspend fun getBids(coin: String): ArrayList<BidsModel>
}