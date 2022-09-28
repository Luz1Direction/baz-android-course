package com.example.criptomonedasapp.services

import com.example.criptomonedasapp.model.network.response.CoinsResponseModel
import com.example.criptomonedasapp.model.network.response.OrderCoinResponseModel
import com.example.criptomonedasapp.model.network.response.TickerResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("v3/available_books/")
    suspend fun getCoins(): CoinsResponseModel

    @GET("v3/ticker/")
    suspend fun getDetailsCoin(@Query("book") book: String): TickerResponseModel

    @GET("v3/order_book/")
    suspend fun getAskAndBids(@Query("book") book: String): OrderCoinResponseModel

}