package com.example.criptomonedasapp.domain

import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import com.example.criptomonedasapp.domain.model.CoinDetailModelData
import javax.inject.Inject


class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(coin: String) : CoinDetailModelData? {
       return repository.getCoinDetails(coin)
    }
}