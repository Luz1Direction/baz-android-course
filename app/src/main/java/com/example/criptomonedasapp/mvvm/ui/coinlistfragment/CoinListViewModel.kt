package com.example.criptomonedasapp.mvvm.ui.coinlistfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.config.InitialAplication
import com.example.criptomonedasapp.model.CoinCardModel
import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.model.network.response.CoinResponseModel
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity
import com.example.criptomonedasapp.mvvm.data.database.usecases.CryptocurrenciesDataSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val useCaseDatabase : CryptocurrenciesDataSourceUseCase
    ) : ViewModel() {

    private var _coinListFinal = MutableLiveData<List<CoinCardModel>?>()
    var coinFinalList: MutableLiveData<List<CoinCardModel>?> = _coinListFinal

    private val _coinListObserve = MutableLiveData<List<CoinListModel>>(emptyList())
    val coinListObserve: MutableLiveData<List<CoinListModel>> = _coinListObserve


    fun getAllCoin(){
        viewModelScope.launch {
            _coinListFinal.value = withContext(Dispatchers.IO){
                useCaseDatabase.getCoinList()
            }
            _coinListFinal?.let {
                insertAllCoin()
            }
        }
    }

    private fun insertAllCoin() {
        InitialAplication.webService.getCoins()
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onSuccess: CoinResponseModel?, onError: Throwable? ->
                onSuccess?.let { list ->
                    _coinListObserve.value = list.coinList?.filter {  it.coinName.contains("mxn")}
                }
                onError?.let {
                    _coinListObserve.value = emptyList()
                }
            }
    }

     fun insertCoinListDatabase(coinList: List<CoinCardEntity>) {

        viewModelScope.launch {
            _coinListFinal.value = withContext(Dispatchers.IO) {
                coinList.forEach {
                    useCaseDatabase.insertAllCoin(it)
                }
                useCaseDatabase.getCoinList()
            }
        }
    }
}