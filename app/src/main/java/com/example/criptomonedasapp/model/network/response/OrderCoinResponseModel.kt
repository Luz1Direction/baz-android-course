package com.example.criptomonedasapp.model.network.response

import com.example.criptomonedasapp.model.network.AksAndBidsModel
import com.google.gson.annotations.SerializedName

data class OrderCoinResponseModel(
    var success: String,
    @SerializedName("payload") var CoinList: AksAndBidsModel
)
