package com.example.criptomonedasapp.model.network

import com.google.gson.annotations.SerializedName

data class CoinDetailModel(
    @SerializedName("high") val highValue: String,
    @SerializedName("last") val lastValue: String,
    @SerializedName("book") val coinName: String,
    val volume: String,
    @SerializedName("low") val lowValue: String,
    val ask: String,
    val bid: String
){
    fun isValid() = this != null
}




