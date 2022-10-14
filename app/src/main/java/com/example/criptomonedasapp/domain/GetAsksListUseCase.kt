package com.example.criptomonedasapp.domain

import com.example.criptomonedasapp.data.local.database.entities.toDatabase
import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import com.example.criptomonedasapp.domain.model.AsksModelData
import javax.inject.Inject

class GetAsksListUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(coin: String): List<AsksModelData> {
        val asks = repository.getAsksFromApi(coin)

       return if (asks != null) {
           repository.deleteAllAsks(coin)
            repository.insertAsks(asks.map { it.toDatabase() })
            asks
        } else {
            repository.getAsksFromDatabase(coin)
        }
    }
}