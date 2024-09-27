package com.example.criptomonedasapp.domain

import com.example.criptomonedasapp.domain.model.CoinCardData
import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(coins: List<CoinCardData>): List<CoinCardData> {
        return repository.getAllCoins(coins)
    }
}