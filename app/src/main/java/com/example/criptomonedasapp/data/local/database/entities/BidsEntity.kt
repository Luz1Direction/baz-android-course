package com.example.criptomonedasapp.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.criptomonedasapp.domain.model.BidsModelData

@Entity(tableName = "bids_table")
data class BidsEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val price: String,
    val coinName: String,
    val amount: String
)

fun BidsModelData.toDatabase() = BidsEntity(price = price, coinName = coinName, amount = amount)
