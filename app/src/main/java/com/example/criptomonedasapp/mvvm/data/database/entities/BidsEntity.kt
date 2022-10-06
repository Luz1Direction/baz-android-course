package com.example.criptomonedasapp.mvvm.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bids_table")
data class BidsEntity(
    @PrimaryKey
    val price: String,
    val coinName: String,
    val amount: String
)
