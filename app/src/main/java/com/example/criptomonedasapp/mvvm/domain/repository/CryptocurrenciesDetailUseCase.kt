package com.example.criptomonedasapp.mvvm.domain.repository

import com.example.criptomonedasapp.model.network.*
import javax.inject.Inject

interface CryptocurrenciesDetailUseCase {
    suspend fun getCoinDetails(coin: String): CoinDetailModel?
    suspend fun getAsksAndBids(coin: String): AsksAndBidsModel?
}