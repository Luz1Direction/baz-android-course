package com.example.criptomonedasapp.domain

import com.example.criptomonedasapp.data.local.database.entities.toDatabase
import com.example.criptomonedasapp.domain.model.CoinCardData
import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
        suspend operator fun invoke(coins: List<CoinCardData>?): List<CoinCardData> {

            return if (coins != null) {
                repository.insertCoinList(coins.map { it.toDatabase() })
                coins
            } else {
                repository.getAllCoinsFromDatabase()
            }
        }
}