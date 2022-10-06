package com.example.criptomonedasapp.mvvm.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_list_table")
data class CoinCardEntity(
    @PrimaryKey
    val id: String,
    val coinName: String,
    val drawable: Int,
    val maxValue: String,
    val minValue:String
)
