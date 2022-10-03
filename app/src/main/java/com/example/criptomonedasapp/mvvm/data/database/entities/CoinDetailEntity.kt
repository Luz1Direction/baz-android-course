package com.example.criptomonedasapp.mvvm.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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
