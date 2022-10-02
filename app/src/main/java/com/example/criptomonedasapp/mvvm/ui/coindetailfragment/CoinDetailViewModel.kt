package com.example.criptomonedasapp.mvvm.ui.coindetailfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.model.network.*
import com.example.criptomonedasapp.mvvm.data.database.entities.AsksEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinDetailEntity
import com.example.criptomonedasapp.mvvm.data.database.usecases.CryptocurrenciesDataSourceUseCase
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesRemoteDataSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private var useCase : CryptocurrenciesRemoteDataSourceUseCase,
    private var useCaseDatabase : CryptocurrenciesDataSourceUseCase) : ViewModel() {

    var coinDetailModel: CoinDetailModel? = null

    private var asksAndBids: AsksAndBidsModel? = null

    private var _bidsList = MutableLiveData<List<BidsModel>?>()
    var bidsList: MutableLiveData<List<BidsModel>?> = _bidsList

    private var _asksList = MutableLiveData<List<AsksModel>?>()
    var asksList: MutableLiveData<List<AsksModel>?> = _asksList

    private var _coinDetailFinal = MutableLiveData<CoinDetailModel?>()
    var coinDetailFinal: MutableLiveData<CoinDetailModel?> = _coinDetailFinal

    fun getCoinDetail(nameCoin: String) {
        viewModelScope.launch {
            coinDetailModel = withContext(Dispatchers.Unconfined) {
                useCase.getCoinDetails(nameCoin)
            }

            if(isValid(coinDetailModel)){
                coinDetailModel?.let {
                    insertCoinDetailDatabase(
                        CoinDetailEntity(
                            coinName = it.coinName  ,
                            highValue = it.highValue,
                            lastValue = it.lastValue,
                            volume = it.volume,
                            lowValue = it.lowValue,
                            ask = it.ask,
                            bid = it.bid
                        ))
                }
            } else {
                _coinDetailFinal.value = useCaseDatabase.getCoinDetail(nameCoin)
            }
        }
    }

    fun getAsksAndBids(nameCoin: String){
        viewModelScope.launch {
            _asksList.value =  withContext(Dispatchers.IO){
                useCaseDatabase.getAsks(nameCoin)
            }
            _bidsList.value =  withContext(Dispatchers.IO){
                useCaseDatabase.getBids(nameCoin)
            }
                _asksList?.let {
                getAsksAndBidsFromRemoteDataSource(nameCoin)
            }
        }
    }

    private fun getAsksAndBidsFromRemoteDataSource(nameCoin: String) {
        var asksEntityList = ArrayList<AsksEntity>()
        viewModelScope.launch {
            asksAndBids = withContext(Dispatchers.IO) {
                useCase.getAsksAndBids(nameCoin)
            }
                asksAndBids?.asks?.let {
                    it.forEach { asks ->
                        asksEntityList.add(AsksEntity(asks.price, asks.coinName, asks.amount))
                    }
                }
                insertAsks(asksEntityList, nameCoin)
                asksAndBids?.bids?.let { insertBids(it, nameCoin) }
            }
    }

    private fun insertCoinDetailDatabase(coinDetail: CoinDetailEntity) {
        viewModelScope.launch {
            _coinDetailFinal.value = withContext(Dispatchers.IO) {
                useCaseDatabase.insertCoinDetail(coinDetail)
                useCaseDatabase.getCoinDetail(coinDetail.coinName)
            }
        }
    }

    private fun insertBids(bids: List<BidsModel>, coinName: String){
        viewModelScope.launch {
            _bidsList.value =  withContext(Dispatchers.IO) {
                useCaseDatabase.insertBids(bids)
                useCaseDatabase.getBids(coinName)
            }
        }
    }

    private fun insertAsks(asks: List<AsksEntity>, coinName: String){
        viewModelScope.launch {
            _asksList.value   =  withContext(Dispatchers.IO) {
                useCaseDatabase.insertAsks(asks)
                useCaseDatabase.getAsks(coinName)
            }
        }
    }
}