package com.example.criptomonedasapp.mvvm.iu.coindetailfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.model.network.AsksAndBidsModel
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.database.usecases.CryptocurrenciesDatabaseUseCase
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailViewModel(private var useCase : CryptocurrenciesUseCase, private var useCaseDatabase : CryptocurrenciesDatabaseUseCase) : ViewModel() {

    private lateinit var coinModel: CoinDetailModel
    var coinDetailModel: CoinDetailModel? = null

    private lateinit var asksAndBids: AsksAndBidsModel

    private var _bidsList = MutableLiveData<List<BidsModel>>()
    var bidsList: LiveData<List<BidsModel>> = _bidsList

    private var _asksList = MutableLiveData<List<AsksModel>>()
    var asksList: LiveData<List<AsksModel>> = _asksList

    private var _coinDetailFinal = MutableLiveData<CoinDetailModel>()
    var coinDetailFinal: LiveData<CoinDetailModel> = _coinDetailFinal

    fun getCoinDetail(nameCoin: String) {
        viewModelScope.launch {
            coinModel = withContext(Dispatchers.IO) {
                useCase.getCoinDetails(nameCoin)
            }
            if(coinModel.coinName != ""){
                insertCoinDetailDatabase(coinModel)
            }else {
                getCoinDetailDatabase(nameCoin)
            }
        }
    }

    fun getAsksAndBids(nameCoin: String) {
        viewModelScope.launch {
            asksAndBids = withContext(Dispatchers.IO) {
                useCase.getAsksAndBids(nameCoin)
            }
            if(asksAndBids.bids.isNotEmpty()){
                insertAsksAndBids(asksAndBids.asks, asksAndBids.bids, nameCoin)
            } else {
                getAsksDatabase(nameCoin)
                getBidsDatabase(nameCoin)
            }
        }
    }

    private fun insertCoinDetailDatabase(coinDetail: CoinDetailModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCaseDatabase.insertCoinDetail(coinDetail)
                getCoinDetailDatabase(coinDetail.coinName)
            }
        }
    }

    private fun getCoinDetailDatabase(coin: String) {
        viewModelScope.launch {
            coinDetailModel = withContext(Dispatchers.IO) {
                useCaseDatabase.getCoinDetail(coin)
            }
            coinDetailModel?.let {
                _coinDetailFinal.value = coinDetailModel
            }
        }
    }

    private fun insertAsksAndBids(asks: List<AsksModel>, bids: List<BidsModel>, nameCoin: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCaseDatabase.insertAsks(asks)
                useCaseDatabase.insertBids(bids)
                getAsksDatabase(nameCoin)
                getBidsDatabase(nameCoin)
            }
        }
    }

    private fun getAsksDatabase(coin: String){
        viewModelScope.launch {
            val resultAsks = withContext((Dispatchers.IO)){
               useCaseDatabase.getAsks(coin)
            }
            _asksList.value = resultAsks
        }
    }

    private fun getBidsDatabase(coin: String){
        viewModelScope.launch {
            val resultBids = withContext((Dispatchers.IO)){
               useCaseDatabase.getBids(coin)
            }
            _bidsList.value = resultBids
        }
    }
}