package com.example.criptomonedasapp.mvvm.data.database.domain.repository

import com.example.criptomonedasapp.model.CoinCardModel
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.database.entities.AsksEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.BidsEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinDetailEntity

interface CryptocurrenciesDataSource {
    suspend fun insertAllCoin(coins: CoinCardEntity)
    suspend fun getCoinList(): ArrayList<CoinCardModel>
    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity)
    suspend fun getCoinDetail(coin: String): CoinDetailModel
    suspend fun insertAsks(coins: List<AsksEntity>)
    suspend fun getAsks(coin: String): ArrayList<AsksModel>
    suspend fun insertBids(coins: List<BidsEntity>)
    suspend fun getBids(coin: String): ArrayList<BidsModel>
}