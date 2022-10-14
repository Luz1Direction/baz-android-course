package com.example.criptomonedasapp.domain

import com.example.criptomonedasapp.data.local.database.entities.toDatabase
import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import com.example.criptomonedasapp.domain.model.BidsModelData
import javax.inject.Inject

class GetBidsListUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(coin: String): List<BidsModelData> {
        val bids = repository.getBidsFromApi(coin)

        return if (bids != null) {
            repository.deleteAllBids(coin)
            repository.insertBids(bids.map { it.toDatabase() })
            bids
        } else {
            repository.getBidsFromDatabase(coin)
        }
    }
}