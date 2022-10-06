package com.example.criptomonedasapp.mvvm.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asks_table")
data class AsksEntity(
    @PrimaryKey
    val price: String,
    val coinName: String,
    val amount: String
)
