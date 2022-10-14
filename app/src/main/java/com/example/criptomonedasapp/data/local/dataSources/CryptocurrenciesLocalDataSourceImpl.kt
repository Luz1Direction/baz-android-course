package com.example.criptomonedasapp.data.local.dataSources

import com.example.criptomonedasapp.data.network.model.CoinCardModel
import com.example.criptomonedasapp.data.network.model.network.getCoinModel
import com.example.criptomonedasapp.data.local.database.CoinsDatabase
import com.example.criptomonedasapp.data.local.CryptocurrenciesLocalDataSource
import com.example.criptomonedasapp.data.local.database.entities.AsksEntity
import com.example.criptomonedasapp.data.local.database.entities.BidsEntity
import com.example.criptomonedasapp.data.local.database.entities.CoinCardEntity
import com.example.criptomonedasapp.data.local.database.entities.CoinDetailEntity
import javax.inject.Inject

class CryptocurrenciesLocalDataSourceImpl @Inject constructor(
    private val cryptocurrenciesBD : CoinsDatabase
): CryptocurrenciesLocalDataSource {

    override suspend fun insertAllCoin(coins: List<CoinCardEntity>) {
        coins.forEach {
            cryptocurrenciesBD.cryptocurrenciesDao().insertAllCoin(it)
        }
    }

    override suspend fun getCoinList(): List<CoinCardEntity> {
        return cryptocurrenciesBD.cryptocurrenciesDao().getCoinList()
    }

    override suspend fun insertCoinDetail(coinDetail: CoinDetailEntity) {
        cryptocurrenciesBD.cryptocurrenciesDao().insertCoinDetail(
            coinDetail
        )
    }

    override suspend fun getCoinDetail(coin: String): CoinDetailEntity {
        return cryptocurrenciesBD.cryptocurrenciesDao().getCoinDetail(coin)
    }

    override suspend fun insertAsks(coins: List<AsksEntity>) {
        coins.forEach {
            cryptocurrenciesBD.cryptocurrenciesDao().insertAsks(it)
        }
    }

    override suspend fun getAsks(coin: String): List<AsksEntity> {
       return cryptocurrenciesBD.cryptocurrenciesDao().getAsks(coin)
    }

    override suspend fun insertBids(coins: List<BidsEntity>) {
        coins.forEach {
            cryptocurrenciesBD.cryptocurrenciesDao().insertBids(
                it
            )
        }
    }

    override suspend fun getBids(coin: String): List<BidsEntity> {
        return cryptocurrenciesBD.cryptocurrenciesDao().getBids(coin)
    }

    override suspend fun deleteAllAsks(coin: String) {
        cryptocurrenciesBD.cryptocurrenciesDao().deleteAllAsks(coin)
    }

    override suspend fun deleteAllBids(coin: String) {
        cryptocurrenciesBD.cryptocurrenciesDao().deleteAllBids(coin)
    }
}