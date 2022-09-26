package com.example.criptomonedasapp.model.network.response

import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.google.gson.annotations.SerializedName

data class TickerResponseModel (
    var success: String,
    @SerializedName("payload") var detailsCoin: CoinDetailModel
    )