package com.example.criptomonedasapp.mvvm.data.repository

import android.util.Log
import com.example.criptomonedasapp.config.InitialAplication.Companion.webService
import com.example.criptomonedasapp.model.network.*
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRepository
import retrofit2.HttpException
import java.io.IOException

class CryptocurrenciesRepositoryImpl: CryptocurrenciesRepository {

    override suspend fun getCoinList(): List<CoinListModel> {
        return try {
            val obtainedList = webService.getCoins()
            obtainedList.CoinList
        } catch (e: HttpException) {
            Log.i("error","An unexpected error occurred")
            emptyList<CoinListModel>()
        } catch (e: IOException) {
            Log.i("error","Couldn't reach server. Check your internet connection.")
            emptyList<CoinListModel>()
        }

}

    override suspend fun getCoinDetails(coin: String): CoinDetailModel {
        return try {
            val obtainedResult = webService.getDetailsCoin(coin)
            obtainedResult.detailsCoin
        } catch (e: HttpException) {
            Log.i("error","An unexpected error occurred")
            CoinDetailModel("","","","","","","")
        } catch (e: IOException) {
            Log.i("error","Couldn't reach server. Check your internet connection.")
            CoinDetailModel("","","","","","","")
        }

    }

    override suspend fun getAsksAndBids(coin: String): AsksAndBidsModel {
        return try {
            val obtainedList = webService.getAskAndBids(coin)
            obtainedList.CoinList
        }catch (e: HttpException) {
            Log.i("error","An unexpected error occurred")
            AsksAndBidsModel()
        } catch (e: IOException) {
            Log.i("error","Couldn't reach server. Check your internet connection.")
            AsksAndBidsModel()
        }
    }

}

