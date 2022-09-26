package com.example.criptomonedasapp.mvvm.iu.coindetailfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesRepositoryImpl
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailViewModel : ViewModel() {

    var  repository : CryptocurrenciesRepositoryImpl = CryptocurrenciesRepositoryImpl()
    private lateinit var list: CoinDetailModel

    private val _detail = MutableLiveData<CoinDetailModel>()
    val detail: LiveData<CoinDetailModel> = _detail

    fun getDetailCoin(nameCoin: String) {
        viewModelScope.launch {
            list = withContext(Dispatchers.IO) {
                repository.getCoinDetails(nameCoin)
            }
            _detail.value = list
        }
    }


}