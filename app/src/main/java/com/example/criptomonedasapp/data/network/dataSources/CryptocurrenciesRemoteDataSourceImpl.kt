package com.example.criptomonedasapp.data.network.dataSources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.criptomonedasapp.data.network.model.network.*
import com.example.criptomonedasapp.data.network.CryptocurrenciesRemoteDataSource
import com.example.criptomonedasapp.data.network.model.network.response.CoinResponseModel
import com.example.criptomonedasapp.data.network.services.APIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptocurrenciesRemoteDataSourceImpl @Inject constructor(private val api: APIService):
    CryptocurrenciesRemoteDataSource {


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

