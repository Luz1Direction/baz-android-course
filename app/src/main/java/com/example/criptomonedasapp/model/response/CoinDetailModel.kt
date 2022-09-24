package com.example.criptomonedasapp.model

import com.google.gson.annotations.SerializedName

data class CoinDetailModel(
    @SerializedName("high") var highValue: String,
    @SerializedName("last") var lastValue: String,
    var created_at: String,
    @SerializedName("book") var coinName: String,
    var volume: String,
    var vwap: String,
    @SerializedName("low") var lowValue: String,
    var ask: String,
    var bid: String,
    var change_24: String,
    var rolling_average_change: RollingAverageChange,
)


data class RollingAverageChange(
    @SerializedName("6") var rolling_average_change: String,
)
