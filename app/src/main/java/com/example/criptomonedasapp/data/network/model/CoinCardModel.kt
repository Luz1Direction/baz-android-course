package com.example.criptomonedasapp.data.network.model

import com.example.criptomonedasapp.data.network.model.network.AsksModel
import com.example.criptomonedasapp.data.network.model.network.CoinListModel
import com.example.criptomonedasapp.data.network.model.network.getCoinModel
import com.example.criptomonedasapp.domain.model.AsksModelData

data class CoinCardModel(
    val coinName: String,
    val id: String,
    val drawable: Int,
    val maxValue: String,
    val minValue:String
)

fun CoinListModel.toDomain() = CoinCardModel(
    coinName = getCoinModel(coinName).coinName,
    id = coinName,
    drawable = getCoinModel(coinName).drawable,
    maxValue = maximumValue,
    minValue = minimumValue)
