package com.example.criptomonedasapp.domain

import com.example.criptomonedasapp.data.local.database.entities.toDatabase
import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import com.example.criptomonedasapp.domain.model.CoinDetailModelData
import javax.inject.Inject


class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(coin: String) : CoinDetailModelData {
        val details = repository.getCoinDetailsFromApi(coin)
       return if(details != null){
            repository.insertCoinDetail(details.toDatabase())
            details
        }else {
            repository.getCoinDetailsFromDatabase(coin)
        }
    }
}