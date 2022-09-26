package com.example.criptomonedasapp.model.network.response

import com.example.criptomonedasapp.model.network.CoinListModel
import com.google.gson.annotations.SerializedName

data class CoinsResponseModel (
    var success: String,
    @SerializedName("payload") var CoinList: List<CoinListModel>
)




