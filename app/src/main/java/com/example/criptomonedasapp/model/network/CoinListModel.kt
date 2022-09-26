package com.example.criptomonedasapp.model.network

import com.google.gson.annotations.SerializedName

data class CoinListModel(
    @SerializedName("book") var coinName: String,
    var minimum_price: String,
    var maximum_price: String,
    var minimum_amount: String,
    var maximum_amount: String,
    var minimum_value: String,
    var maximum_value: String,
    var tick_size: String,
    var default_chart: String,
    var fees: FeesModel
)
