package com.example.criptomonedasapp.mvvm.data.database.usecases

import com.example.criptomonedasapp.model.CoinsModelCard
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.database.domain.repository.CryptocurrenciesDatabaseRepository
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity

class CryptocurrenciesDatabaseUseCase(var repository: CryptocurrenciesDatabaseRepository) {
    suspend fun insertAllCoin(coins: CoinCardEntity) = repository.insertAllCoin(coins)
    suspend fun getCoinList(): ArrayList<CoinsModelCard> = repository.getCoinList()
    suspend fun insertCoinDetail(coinDetail: CoinDetailModel) = repository.insertCoinDetail(coinDetail)
    suspend fun getCoinDetail(coin: String): CoinDetailModel = repository.getCoinDetail(coin)
    suspend fun insertAsks(coins: List<AsksModel>) = repository.insertAsks(coins)
    suspend fun getAsks(coin: String): ArrayList<AsksModel> = repository.getAsks(coin)
    suspend fun insertBids(coins: List<BidsModel>) = repository.insertBids(coins)
    suspend fun getBids(coin: String): ArrayList<BidsModel> = repository.getBids(coin)
}