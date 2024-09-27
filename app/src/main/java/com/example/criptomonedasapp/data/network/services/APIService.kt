package com.example.criptomonedasapp.data.network.services

import com.example.criptomonedasapp.data.network.model.network.response.CoinOrderResponseModel
import com.example.criptomonedasapp.data.network.model.network.response.CoinResponseModel
import com.example.criptomonedasapp.data.network.model.network.response.TickerResponseModel
import com.example.criptomonedasapp.data.network.services.CryptoEndPoints.ALL_COINS
import com.example.criptomonedasapp.data.network.services.CryptoEndPoints.COIN_DETAIL
import com.example.criptomonedasapp.data.network.services.CryptoEndPoints.ORDER_COINS
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(ALL_COINS)
    fun getCoins(): Single<CoinResponseModel>

    @GET(COIN_DETAIL)
    suspend fun getCoinDetail(@Query("book") book: String): TickerResponseModel

    @GET(ORDER_COINS)
    suspend fun getAskAndBids(@Query("book") book: String): CoinOrderResponseModel

}