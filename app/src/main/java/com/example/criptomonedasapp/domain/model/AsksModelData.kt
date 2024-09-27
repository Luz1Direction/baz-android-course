package com.example.criptomonedasapp.domain.model

import com.example.criptomonedasapp.data.local.database.entities.AsksEntity
import com.example.criptomonedasapp.data.network.model.network.AsksModel

data class AsksModelData (
    val price: String,
    val coinName: String,
    val amount: String
)

fun AsksModel.toDomain() = AsksModelData(price, coinName, amount)

fun AsksEntity.toDomain() = AsksModelData(price = price, coinName =  coinName, amount = amount)
