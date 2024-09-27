package com.example.criptomonedasapp.domain.model

import com.example.criptomonedasapp.data.local.database.entities.CoinDetailEntity
import com.example.criptomonedasapp.data.network.model.network.CoinDetailModel


data class CoinDetailModelData (
    val coinName: String,
    val highValue: String,
    val lastValue: String,
    val volume: String,
    val lowValue: String,
    val ask: String,
    val bid: String
)



fun CoinDetailModel.toDomain() = CoinDetailModelData(coinName,highValue,lastValue, volume, lowValue, ask, bid)

fun CoinDetailEntity.toDomain() = CoinDetailModelData(coinName,highValue,lastValue, volume, lowValue, ask, bid)