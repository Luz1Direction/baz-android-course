package com.example.criptomonedasapp.model.network

import com.google.gson.annotations.SerializedName

data class BidsModel(
    @SerializedName("book") val coinName: String,
    val price: String,
    val amount: String
)