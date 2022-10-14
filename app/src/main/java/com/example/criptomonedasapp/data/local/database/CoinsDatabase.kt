package com.example.criptomonedasapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.criptomonedasapp.data.local.database.dao.CoinsDao
import com.example.criptomonedasapp.data.local.database.entities.AsksEntity
import com.example.criptomonedasapp.data.local.database.entities.BidsEntity
import com.example.criptomonedasapp.data.local.database.entities.CoinCardEntity
import com.example.criptomonedasapp.data.local.database.entities.CoinDetailEntity

@Database(entities = [CoinCardEntity::class, CoinDetailEntity::class, AsksEntity::class,BidsEntity::class], version = 1)

abstract class CoinsDatabase:RoomDatabase() {
    abstract fun cryptocurrenciesDao(): CoinsDao
}