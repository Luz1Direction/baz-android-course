package com.example.criptomonedasapp.mvvm.domain.repository

import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.model.network.CoinListModel

interface CryptocurrenciesRepository {
    fun getCoinList(): List<CoinListModel>
    fun getCoinDetails(coin: String): CoinDetailModel
    fun getAskCoin(coin: String): List<AsksModel>
    fun getBidsCoin(coin: String): List<BidsModel>
}