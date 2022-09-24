package com.example.criptomonedasapp.model.response

import com.example.criptomonedasapp.model.CoinDetailModel
import com.google.gson.annotations.SerializedName

data class TickerResponseModel (
    var success: String,
    @SerializedName("payload") var detailsCoin: CoinDetailModel
    )