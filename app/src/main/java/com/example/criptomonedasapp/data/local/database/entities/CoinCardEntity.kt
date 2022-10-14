package com.example.criptomonedasapp.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.criptomonedasapp.domain.model.CoinCardData

@Entity(tableName = "coin_list_table")
data class CoinCardEntity(
    @PrimaryKey
    val id: String,
    val coinName: String,
    val drawable: Int,
    val maxValue: String,
    val minValue:String
)

fun CoinCardData.toDatabase() = CoinCardEntity(id, coinName, drawable, maxValue, minValue)
