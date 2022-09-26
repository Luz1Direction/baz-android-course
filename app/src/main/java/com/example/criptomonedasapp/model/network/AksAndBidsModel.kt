package com.example.criptomonedasapp.model.network

import com.google.gson.annotations.SerializedName

data class AksAndBidsModel(
    var asks : List<AsksModel>,
    var bids : List<BidsModel>
)

data class AsksModel(
    @SerializedName("book") var coinName: String,
    var price: String,
    var amount: String
)

data class BidsModel(
    @SerializedName("book") var coinName: String,
    var price: String,
    var amount: String
)
