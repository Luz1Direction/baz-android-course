package com.example.criptomonedasapp.mvvm.data.repository

import com.example.criptomonedasapp.config.InitialAplication.Companion.webService
import com.example.criptomonedasapp.model.network.*
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRepository

class CryptocurrenciesRepositoryImpl: CryptocurrenciesRepository {

    override suspend fun getCoinList(): List<CoinListModel> {
        var obtainedList = webService.getCoins()
        return obtainedList.CoinList
    }

    override suspend fun getCoinDetails(coin: String): CoinDetailModel {
        var obtainedResult = webService.getDetailsCoin(coin)
        return obtainedResult.detailsCoin
    }

    override suspend fun getAsksAndBids(coin: String): AsksAndBidsModel {
        var obtainedList = webService.getAskAndBids(coin)
        return obtainedList.CoinList
    }

}

