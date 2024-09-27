package com.example.criptomonedasapp.domain

import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import com.example.criptomonedasapp.domain.model.BidsModelData
import javax.inject.Inject

class GetBidsListUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(coin: String): List<BidsModelData> {
        return repository.getBids(coin)
    }
}