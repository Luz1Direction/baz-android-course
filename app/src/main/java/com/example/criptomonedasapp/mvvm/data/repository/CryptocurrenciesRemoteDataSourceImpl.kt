package com.example.criptomonedasapp.mvvm.data.repository

import android.util.Log
import com.example.criptomonedasapp.config.InitialAplication.Companion.webService
import com.example.criptomonedasapp.model.network.*
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRemoteDataSource
import com.example.criptomonedasapp.services.APIService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptocurrenciesRemoteDataSourceImpl @Inject constructor(): CryptocurrenciesRemoteDataSource {

    override suspend fun getCoinList(): List<CoinListModel>? {
        return try {
            val obtainedList = webService.getCoins()
            obtainedList.coinList
        } catch (e: HttpException) {
            Log.i("error","An unexpected error occurred")
            null
        } catch (e: IOException) {
            Log.i("error","Couldn't reach server. Check your internet connection.")
            null
        }

}

    override suspend fun getCoinDetails(coin: String): CoinDetailModel? {
        return try {
            val obtainedResult = webService.getCoinDetail(coin)
            obtainedResult.coinDetail
        } catch (e: HttpException) {
            Log.i("error","An unexpected error occurred")
            null
        } catch (e: IOException) {
            Log.i("error","Couldn't reach server. Check your internet connection.")
            null
        }

    }

    override suspend fun getAsksAndBids(coin: String): AsksAndBidsModel? {
        return try {
            val obtainedList = webService.getAskAndBids(coin)
            obtainedList.coinList
        }catch (e: HttpException) {
            Log.i("error","An unexpected error occurred")
            null
        } catch (e: IOException) {
            Log.i("error","Couldn't reach server. Check your internet connection.")
            null
        }
    }

}

