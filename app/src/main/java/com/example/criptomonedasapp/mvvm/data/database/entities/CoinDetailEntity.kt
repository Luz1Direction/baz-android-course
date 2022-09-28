package com.example.criptomonedasapp.mvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coin_detail_table")
data class CoinDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "coinName") val coinName: String,
    @ColumnInfo(name = "highValue") val highValue: String,
    @ColumnInfo(name = "lastValue") val lastValue: String,
    @ColumnInfo(name = "volume") val volume: String,
    @ColumnInfo(name = "lowValue") val lowValue: String,
    @ColumnInfo(name = "ask")  val ask: String,
    @ColumnInfo(name = "bid")  val bid: String
)
