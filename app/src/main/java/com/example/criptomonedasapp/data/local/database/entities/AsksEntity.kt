package com.example.criptomonedasapp.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.criptomonedasapp.domain.model.AsksModelData

@Entity(tableName = "asks_table")
data class AsksEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val price: String,
    val coinName: String,
    val amount: String
)

fun AsksModelData.toDatabase() = AsksEntity(price = price, coinName = coinName, amount = amount)

