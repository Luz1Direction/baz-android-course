package com.example.criptomonedasapp.mvvm.iu.coindetailfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.model.network.AsksAndBidsModel
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesRepositoryImpl
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesUseCase
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailViewModel(var useCase : CryptocurrenciesUseCase) : ViewModel() {

    private lateinit var list: CoinDetailModel
    private val _coinDetailModel = MutableLiveData<CoinDetailModel>()
    val coinDetailModel: LiveData<CoinDetailModel> = _coinDetailModel

    private lateinit var asksAndBids: AsksAndBidsModel

    var _bidsList = MutableLiveData<List<BidsModel>>()
    var bidsList: LiveData<List<BidsModel>> = _bidsList

    var _asksList = MutableLiveData<List<AsksModel>>()
    var asksList: LiveData<List<AsksModel>> = _asksList

    fun getCoinDetail(nameCoin: String) {
        viewModelScope.launch {
            list = withContext(Dispatchers.IO) {
                useCase.getCoinDetails(nameCoin)
            }
            _coinDetailModel.value = list
        }
    }

    fun getAsksAndBids(nameCoin: String){
        viewModelScope.launch {
            asksAndBids = withContext(Dispatchers.IO){
                useCase.getAsksAndBids(nameCoin)
            }
                _asksList.value = asksAndBids.asks
                _bidsList.value = asksAndBids.bids
        }
    }
}