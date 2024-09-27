package com.example.criptomonedasapp.domain.dataSources

import com.example.criptomonedasapp.data.local.CryptocurrenciesLocalDataSource
import com.example.criptomonedasapp.data.local.database.entities.toDatabase
import com.example.criptomonedasapp.data.network.CryptocurrenciesRemoteDataSource
import com.example.criptomonedasapp.domain.model.*
import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import javax.inject.Inject

class CryptocurrenciesRepositoryImpl @Inject constructor(
    private val remoteDataSource: CryptocurrenciesRemoteDataSource,
    private val localDataSource: CryptocurrenciesLocalDataSource): CryptocurrenciesRepository
{

    override suspend  fun getAllCoins(coins: List<CoinCardData>): List<CoinCardData> {
        return if (coins.isNotEmpty()) {
            localDataSource.insertAllCoin(coins.map { it.toDatabase() })
            coins
        } else {
            localDataSource.getCoinList().map { it.toDomain() }
        }
    }



    override suspend fun getCoinDetails(coin: String): CoinDetailModelData? {
        val details = remoteDataSource.getCoinDetails(coin)?.toDomain()
        return if(details != null){
            localDataSource.insertCoinDetail(details.toDatabase())
            details
        }else {
            localDataSource.getCoinDetail(coin)?.toDomain()
        }
    }

    override suspend fun getAsks(coin: String): List<AsksModelData> {
        val asks = remoteDataSource.getAsksAndBids(coin)?.asks?.map { it.toDomain() }

        return if (asks != null) {
            localDataSource.deleteAllAsks(coin)
            localDataSource.insertAsks(asks.map { it.toDatabase() })
            asks
        } else {
            localDataSource.getAsks(coin).map { it.toDomain() }
        }
    }


    override suspend fun getBids(coin: String): List<BidsModelData> {
        val bids = remoteDataSource.getAsksAndBids(coin)?.bids?.map { it.toDomain() }

        return if (bids != null) {
            localDataSource.deleteAllBids(coin)
            localDataSource.insertBids(bids.map { it.toDatabase() })
            bids
        } else {
            localDataSource.getBids(coin).map { it.toDomain() }
        }
    }

}