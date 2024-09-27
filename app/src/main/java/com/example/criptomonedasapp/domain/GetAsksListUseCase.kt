package com.example.criptomonedasapp.domain

import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import com.example.criptomonedasapp.domain.model.AsksModelData
import javax.inject.Inject

class GetAsksListUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(coin: String): List<AsksModelData> {
       return repository.getAsks(coin)
    }
}