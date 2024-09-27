package com.example.criptomonedasapp.domain.model

import com.example.criptomonedasapp.data.local.database.entities.CoinCardEntity
import com.example.criptomonedasapp.data.network.model.CoinCardModel


data class CoinCardData (
    val id: String,
    val coinName: String,
    val drawable: Int,
    val maxValue: String,
    val minValue:String
)

fun CoinCardModel.toDomain() = CoinCardData(id,coinName,drawable, maxValue, minValue)

fun CoinCardEntity.toDomain() = CoinCardData(id,coinName,drawable, maxValue, minValue)