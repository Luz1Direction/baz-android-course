package com.example.criptomonedasapp.model.network

import com.google.gson.annotations.SerializedName

data class AsksModel(
    @SerializedName("book") var coinName: String,
    var price: String,
    var amount: String
)
