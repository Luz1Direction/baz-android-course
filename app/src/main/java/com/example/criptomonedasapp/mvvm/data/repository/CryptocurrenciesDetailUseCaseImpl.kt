package com.example.criptomonedasapp.mvvm.data.repository

import android.util.Log
import com.example.criptomonedasapp.model.network.*
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesDetailUseCase
import com.example.criptomonedasapp.services.APIService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptocurrenciesDetailUseCaseImpl @Inject constructor(private val api: APIService): CryptocurrenciesDetailUseCase {

    override suspend fun getCoinDetails(coin: String): CoinDetailModel? {
        return try {
            val obtainedResult = api.getCoinDetail(coin)
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
            val obtainedList = api.getAskAndBids(coin)
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

