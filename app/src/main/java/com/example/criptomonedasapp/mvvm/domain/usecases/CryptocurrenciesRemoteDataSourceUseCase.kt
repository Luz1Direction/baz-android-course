package com.example.criptomonedasapp.mvvm.domain.usecases

import com.example.criptomonedasapp.model.network.AsksAndBidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRemoteDataSource
import javax.inject.Inject


class CryptocurrenciesRemoteDataSourceUseCase @Inject constructor(
    private val repository: CryptocurrenciesRemoteDataSource) {
    suspend fun getAvailableCoinList(): List<CoinListModel>? = repository.getCoinList()
    suspend fun getCoinDetails(coin: String): CoinDetailModel? = repository.getCoinDetails(coin)
    suspend fun getAsksAndBids(coin: String): AsksAndBidsModel? = repository.getAsksAndBids(coin)
}