package com.example.criptomonedasapp.model

import com.google.gson.annotations.SerializedName

data class DetailsCoinModel(
    @SerializedName("high") var high: String,
    @SerializedName("last") var last: String,
    @SerializedName("created_at") var created_at: String,
    @SerializedName("book") var book: String,
    @SerializedName("volume") var volume: String,
    @SerializedName("vwap") var vwap: String,
    @SerializedName("low") var low: String,
    @SerializedName("ask") var ask: String,
    @SerializedName("bid") var bid: String,
    @SerializedName("change_24") var change_24: String,
    @SerializedName("rolling_average_change") var rolling_average_change: RollingAverageChange,
)


data class RollingAverageChange(
    @SerializedName("6") var rolling_average_change: String,
)
