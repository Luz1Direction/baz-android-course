package com.example.criptomonedasapp.domain.model

import com.example.criptomonedasapp.data.local.database.entities.BidsEntity
import com.example.criptomonedasapp.data.network.model.network.BidsModel

data class BidsModelData (
    val price: String,
    val coinName: String,
    val amount: String
)

fun BidsModel.toDomain() = BidsModelData(price, coinName, amount)

fun BidsEntity.toDomain() = BidsModelData(price = price, coinName =  coinName, amount = amount)