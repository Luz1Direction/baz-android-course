package com.example.criptomonedasapp.model.network.response

import com.example.criptomonedasapp.model.network.CoinListModel
import com.google.gson.annotations.SerializedName

data class CoinsResponseModel (
    val success: String,
    @SerializedName("payload") val CoinList: List<CoinListModel>
)




