package com.example.criptomonedasapp.model.network

import com.google.gson.annotations.SerializedName

data class CoinDetailModel(
    @SerializedName("high") val highValue: String,
    @SerializedName("last") val lastValue: String,
    val created_at: String,
    @SerializedName("book") val coinName: String,
    val volume: String,
    val vwap: String,
    @SerializedName("low") val lowValue: String,
    val ask: String,
    val bid: String,
    val change_24: String,
    val rolling_average_change: RollingAverageChangeModel,
)
