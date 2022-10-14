package com.example.criptomonedasapp.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.criptomonedasapp.domain.model.CoinDetailModelData

@Entity(tableName = "coin_detail_table")
data class CoinDetailEntity(
    @PrimaryKey
    val coinName: String,
    val highValue: String,
    val lastValue: String,
    val volume: String,
    val lowValue: String,
    val ask: String,
    val bid: String
)

fun CoinDetailModelData.toDatabase() = CoinDetailEntity(coinName, highValue, lastValue,volume, lowValue, ask, bid)
