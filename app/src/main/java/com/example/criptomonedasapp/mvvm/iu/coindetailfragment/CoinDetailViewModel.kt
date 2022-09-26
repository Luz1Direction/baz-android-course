package com.example.criptomonedasapp.mvvm.iu.coindetailfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.adapter.AsksAdapter
import com.example.criptomonedasapp.mvvm.adapter.BidsAdapter
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesRepositoryImpl
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailViewModel : ViewModel() {

    var  repository : CryptocurrenciesRepositoryImpl = CryptocurrenciesRepositoryImpl()
    private lateinit var list: CoinDetailModel

    private val _detail = MutableLiveData<CoinDetailModel>()
    val detail: LiveData<CoinDetailModel> = _detail

    var aksAdapter = MutableLiveData<AsksAdapter>()
    var bidsAdapter = MutableLiveData<BidsAdapter>()

    private lateinit var asksList: List<AsksModel>
    private lateinit var bidsList: List<BidsModel>

    fun getDetailCoin(nameCoin: String) {
        viewModelScope.launch {
            list = withContext(Dispatchers.IO) {
                repository.getCoinDetails(nameCoin)
            }
            _detail.value = list
        }
    }

    fun getAsks(nameCoin: String){
        viewModelScope.launch {
            asksList = withContext(Dispatchers.IO){
                repository.getAskCoin(nameCoin)
            }
            if (asksList.isNotEmpty()){
                aksAdapter.value = AsksAdapter(asksList)
            }
        }
    }

    fun getBids(nameCoin: String){
        viewModelScope.launch {
            bidsList = withContext(Dispatchers.IO){
                repository.getBidsCoin(nameCoin)
            }
            if (bidsList.isNotEmpty()){
                bidsAdapter.value = BidsAdapter(bidsList)
            }
        }
    }




}