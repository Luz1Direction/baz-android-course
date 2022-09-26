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

    var  repository: CryptocurrenciesRepositoryImpl = CryptocurrenciesRepositoryImpl()
    private lateinit var list: CoinDetailModel

    private val _coinDetailModel = MutableLiveData<CoinDetailModel>()
    val coinDetailModell: LiveData<CoinDetailModel> = _coinDetailModel

    private lateinit var asksListObtenied: List<AsksModel>
    private lateinit var bidsListObtenied: List<BidsModel>

    var _bidsList = MutableLiveData<List<BidsModel>>()
    var bidsList: LiveData<List<BidsModel>> = _bidsList

    var _asksList = MutableLiveData<List<AsksModel>>()
    var asksList: LiveData<List<AsksModel>> = _asksList

    fun getCoinDetail(nameCoin: String) {
        viewModelScope.launch {
            list = withContext(Dispatchers.IO) {
                repository.getCoinDetails(nameCoin)
            }
            _coinDetailModel.value = list
        }
    }

    fun getAsks(nameCoin: String){
        viewModelScope.launch {
            asksListObtenied = withContext(Dispatchers.IO){
                repository.getAskCoin(nameCoin)
            }
            if (asksListObtenied.isNotEmpty()){
                _asksList.value = asksListObtenied
            }
        }
    }

    fun getBids(nameCoin: String){
        viewModelScope.launch {
            bidsListObtenied = withContext(Dispatchers.IO){
                repository.getBidsCoin(nameCoin)
            }
            if (bidsListObtenied.isNotEmpty()){
                _bidsList.value = bidsListObtenied
            }
        }
    }
}