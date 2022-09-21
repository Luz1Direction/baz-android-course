package com.example.criptomonedasapp.model.response

import com.example.criptomonedasapp.model.DetailsCoinModel
import com.google.gson.annotations.SerializedName

data class TickerModelResponse (
    @SerializedName("success") var success: String,
    @SerializedName("payload") var detailsCoin: DetailsCoinModel
    )