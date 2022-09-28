package com.example.criptomonedasapp.mvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_list_table")
data class CoinCardEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "coinName") val coinName: String,
    @ColumnInfo(name = "drawable") val drawable: Int,
    @ColumnInfo(name = "maxValue") val maxValue: String,
    @ColumnInfo(name = "minValue") val minValue:String
)
