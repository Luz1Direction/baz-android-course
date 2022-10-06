package com.example.criptomonedasapp.mvvm.domain.usecases

import com.example.criptomonedasapp.model.network.AsksAndBidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesDetailUseCase
import javax.inject.Inject


class CryptocurrenciesDetailsUseCase @Inject constructor(
    private val repository: CryptocurrenciesDetailUseCase) {
    suspend fun getCoinDetails(coin: String): CoinDetailModel? = repository.getCoinDetails(coin)
    suspend fun getAsksAndBids(coin: String): AsksAndBidsModel? = repository.getAsksAndBids(coin)
}