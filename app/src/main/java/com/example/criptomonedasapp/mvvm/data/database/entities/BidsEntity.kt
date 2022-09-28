package com.example.criptomonedasapp.mvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bids_table")
data class BidsEntity(
    @PrimaryKey
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "coinName") val coinName: String,
    @ColumnInfo(name = "amount") val amount: String
)
