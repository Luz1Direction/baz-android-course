package com.example.criptomonedasapp.mvvm.data.repository

import com.example.criptomonedasapp.config.InitialAplication.Companion.webService
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRepository

class CryptocurrenciesRepositoryImpl: CryptocurrenciesRepository {

    private lateinit var coinList: List<CoinListModel>
    private lateinit var details: CoinDetailModel

    override fun getCoinList(): List<CoinListModel> {
        var callResult = webService.getCoins()
        var obtainedList = callResult.execute()

        if (obtainedList.isSuccessful) {
            obtainedList.body()?.let {
                coinList = it.CoinList
            }
        }
        return coinList
    }

    override fun getCoinDetails(coin: String): CoinDetailModel {

        var callResult = webService.getDetailsCoin(coin)
        var obtainedList = callResult.execute()

        if (obtainedList.isSuccessful) {
            obtainedList.body()?.let {
                details = it.detailsCoin
            }
        }

        return details
    }

}

