package com.example.criptomonedasapp.mvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asks_table")
data class AsksEntity(
    @PrimaryKey
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "coinName") val coinName: String,
    @ColumnInfo(name = "amount") val amount: String
)
