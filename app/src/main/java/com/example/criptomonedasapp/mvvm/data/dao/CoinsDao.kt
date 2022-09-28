package com.example.criptomonedasapp.mvvm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.criptomonedasapp.mvvm.data.database.entities.AsksEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.BidsEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinDetailEntity

@Dao
interface CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCoin(coins: CoinCardEntity)

    @Query("SELECT * FROM coin_list_table")
    suspend fun getCoinList():List<CoinCardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetail(coins: CoinDetailEntity)

    @Query("SELECT * FROM coin_detail_table WHERE coinName = :coin")
    suspend fun getCoinDetail(coin: String): CoinDetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsks(coins: AsksEntity)

    @Query("SELECT * FROM asks_table WHERE coinName = :coin")
    suspend fun getAsks(coin: String): List<AsksEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBids(coins: BidsEntity)

    @Query("SELECT * FROM bids_table WHERE coinName = :coin")
    suspend fun getBids(coin: String): List<BidsEntity>

}