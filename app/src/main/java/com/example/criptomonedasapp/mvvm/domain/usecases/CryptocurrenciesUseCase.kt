package com.example.criptomonedasapp.mvvm.domain.usecases

import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRepository


class CryptocurrenciesUseCase(var repository: CryptocurrenciesRepository) {

    fun getAvailableCoinList(): List<CoinListModel> = repository.getCoinList()
}