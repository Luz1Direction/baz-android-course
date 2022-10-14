package com.example.criptomonedasapp.data.network.model.network.response

import com.example.criptomonedasapp.data.network.model.network.CoinDetailModel
import com.google.gson.annotations.SerializedName

data class TickerResponseModel (
    val success: String,
    @SerializedName("payload") val coinDetail: CoinDetailModel
    )