package com.example.criptomonedasapp.model.network.response

import com.example.criptomonedasapp.model.network.AsksAndBidsModel
import com.google.gson.annotations.SerializedName

data class CoinOrderResponseModel(
    val success: String,
    @SerializedName("payload") val coinList: AsksAndBidsModel
)
