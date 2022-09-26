package com.example.criptomonedasapp.model.network

import com.google.gson.annotations.SerializedName

data class RollingAverageChangeModel(
    @SerializedName("6") var rolling_average_change: String,
)