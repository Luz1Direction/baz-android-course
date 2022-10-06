package com.example.criptomonedasapp.mvvm.data.database.usecases

import com.example.criptomonedasapp.model.CoinCardModel
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.database.domain.repository.CryptocurrenciesDataSource
import com.example.criptomonedasapp.mvvm.data.database.entities.AsksEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.BidsEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinDetailEntity
import javax.inject.Inject

class CryptocurrenciesRepository @Inject constructor(private val repository: CryptocurrenciesDataSource) {
    suspend fun insertAllCoin(coins: CoinCardEntity) = repository.insertAllCoin(coins)
    suspend fun getCoinList(): ArrayList<CoinCardModel> = repository.getCoinList()
    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity) = repository.insertCoinDetail(coinDetail)
    suspend fun getCoinDetail(coin: String): CoinDetailModel = repository.getCoinDetail(coin)
    suspend fun insertAsks(coins: List<AsksEntity>) = repository.insertAsks(coins)
    suspend fun getAsks(coin: String): ArrayList<AsksModel> = repository.getAsks(coin)
    suspend fun insertBids(coins: List<BidsEntity>) = repository.insertBids(coins)
    suspend fun getBids(coin: String): ArrayList<BidsModel> = repository.getBids(coin)
}