package com.example.criptomonedasapp.mvvm.data.repository

import com.example.criptomonedasapp.config.InitialAplication.Companion.webService
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRepository

class CryptocurrenciesRepositoryImpl: CryptocurrenciesRepository {

    private lateinit var coinList: List<CoinListModel>
    private lateinit var details: CoinDetailModel
    private lateinit var asksList: List<AsksModel>
    private lateinit var bidsList: List<BidsModel>

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

    override fun getAskCoin(coin: String): List<AsksModel>{
        var callResult = webService.getAskAndBids(coin)
        var obtainedList = callResult.execute()

        if (obtainedList.isSuccessful) {
            obtainedList.body()?.let {
                asksList = it.CoinList.asks
            }
        }
        return asksList
    }

    override fun getBidsCoin(coin: String): List<BidsModel> {
        var callResult = webService.getAskAndBids(coin)
        var obtainedList = callResult.execute()

        if (obtainedList.isSuccessful) {
            obtainedList.body()?.let {
                bidsList = it.CoinList.bids
            }
        }
        return bidsList
    }

}

