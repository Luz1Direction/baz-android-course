package com.example.criptomonedasapp.model.network.response

import com.example.criptomonedasapp.model.network.CoinListModel
import com.google.gson.annotations.SerializedName

data class CoinResponseModel (
    val success: String,
    @SerializedName("payload") val coinList: List<CoinListModel>
)




