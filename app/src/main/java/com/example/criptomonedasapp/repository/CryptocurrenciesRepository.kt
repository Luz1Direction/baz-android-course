package com.example.criptomonedasapp.repository

import com.example.criptomonedasapp.config.InitialAplication.Companion.webService
import com.example.criptomonedasapp.model.CoinDetailModel
import com.example.criptomonedasapp.model.response.CoinList


class CryptocurrenciesRepository {

    private lateinit var coinList: List<CoinList>
    lateinit var details: CoinDetailModel


    fun getCoinList(): List<CoinList> {
        var callResult = webService.getCoins()
        var obtainedList = callResult.execute()

        if (obtainedList.isSuccessful) {
            obtainedList.body()?.let {
                coinList = it.CoinList
            }
        }
        return coinList
    }


    fun getCoinDetails(id: String): CoinDetailModel {
        var callResult = webService.getDetailsCoin(id)
        var obtainedList = callResult.execute()

        if (obtainedList.isSuccessful) {
            obtainedList.body()?.let {
                details = it.detailsCoin
            }
        }

        return details

    }

}

