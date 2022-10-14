package com.example.criptomonedasapp.domain.repository

import com.example.criptomonedasapp.data.local.database.entities.AsksEntity
import com.example.criptomonedasapp.data.local.database.entities.BidsEntity
import com.example.criptomonedasapp.data.local.database.entities.CoinDetailEntity
import com.example.criptomonedasapp.data.local.CryptocurrenciesLocalDataSource
import com.example.criptomonedasapp.data.local.database.entities.CoinCardEntity
import com.example.criptomonedasapp.data.network.CryptocurrenciesRemoteDataSource
import com.example.criptomonedasapp.domain.model.*
import javax.inject.Inject

class CryptocurrenciesRepository @Inject constructor(
    private val remoteDataSource: CryptocurrenciesRemoteDataSource,
    private val localDataSource: CryptocurrenciesLocalDataSource
) {

    suspend fun getAllCoinsFromDatabase(): List<CoinCardData> {
        return localDataSource.getCoinList().map { it.toDomain() }
    }

    suspend fun insertCoinList(coins: List<CoinCardEntity>) {
        localDataSource.insertAllCoin(coins)
    }

    suspend fun getCoinDetailsFromApi(coin: String): CoinDetailModelData? {
        return remoteDataSource.getCoinDetails(coin)?.toDomain()
    }

    suspend fun getCoinDetailsFromDatabase(coin: String): CoinDetailModelData {
        return localDataSource.getCoinDetail(coin).toDomain()
    }

    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity) {
        localDataSource.insertCoinDetail(coinDetail)
    }

    suspend fun getAsksFromApi(coin: String): List<AsksModelData>? {
        return remoteDataSource.getAsksAndBids(coin)?.asks?.map { it.toDomain() }
    }

    suspend fun getAsksFromDatabase(coin: String): List<AsksModelData> {
        return localDataSource.getAsks(coin).map { it.toDomain() }
    }

    suspend fun insertAsks(asksEntity: List<AsksEntity>) {
        localDataSource.insertAsks(asksEntity)
    }

    suspend fun getBidsFromApi(coin: String): List<BidsModelData>? {
        return remoteDataSource.getAsksAndBids(coin)?.bids?.map { it.toDomain() }
    }

    suspend fun getBidsFromDatabase(coin: String): List<BidsModelData> {
        return localDataSource.getBids(coin).map { it.toDomain() }
    }

    suspend fun insertBids(bidsEntity: List<BidsEntity>) {
        localDataSource.insertBids(bidsEntity)
    }

    suspend fun deleteAllAsks(coin: String) {
        localDataSource.deleteAllAsks(coin)
    }

    suspend fun deleteAllBids(coin: String){
        localDataSource.deleteAllBids(coin)
    }

}