package com.example.criptomonedasapp.mvvm.detailscoinfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.repository.CryptocurrenciesRepository
import com.example.criptomonedasapp.model.CoinDetailModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailViewModel : ViewModel() {

    var  repository : CryptocurrenciesRepository = CryptocurrenciesRepository()
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