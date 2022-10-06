package com.example.criptomonedasapp.mvvm.ui.coindetailfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.model.network.AsksAndBidsModel
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.database.entities.AsksEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.BidsEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinDetailEntity
import com.example.criptomonedasapp.mvvm.data.database.usecases.CryptocurrenciesRepository
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private var detailsUseCase : CryptocurrenciesDetailsUseCase,
    private var cryptocurrenciesRepository : CryptocurrenciesRepository) : ViewModel() {

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
                detailsUseCase.getCoinDetails(nameCoin)
            }

            if(coinDetailModel?.isValid() == true){
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
                _coinDetailFinal.value = cryptocurrenciesRepository.getCoinDetail(nameCoin)
            }
        }
    }

    fun getAsksAndBids(nameCoin: String) {
        viewModelScope.launch {

            _asksList.value = withContext(IO) {
                cryptocurrenciesRepository.getAsks(nameCoin)
            }

            _bidsList.value = withContext(IO) {
                cryptocurrenciesRepository.getBids(nameCoin)
            }

            if (_asksList.value.isNullOrEmpty()) {
                getAsksAndBidsFromRemoteDataSource(nameCoin)
            }
        }
    }

    private fun getAsksAndBidsFromRemoteDataSource(coinName: String) {
        var asksEntityList = ArrayList<AsksEntity>()
        var bidsEntityList = ArrayList<BidsEntity>()

        viewModelScope.launch {
            asksAndBids = withContext(Dispatchers.IO) { detailsUseCase.getAsksAndBids(coinName) }

            asksAndBids?.asks?.let {
                it.forEach { asks ->
                    asksEntityList.add(AsksEntity(asks.price, asks.coinName, asks.amount))
                }
                insertAsks(asksEntityList, coinName)
            }

            asksAndBids?.bids?.let {
                it.forEach { bids ->
                    bidsEntityList.add(BidsEntity(bids.price, bids.coinName, bids.amount))
                }
                insertBids(bidsEntityList, coinName)
            }
        }
    }

    private fun insertCoinDetailDatabase(coinDetail: CoinDetailEntity) {
        viewModelScope.launch {
            _coinDetailFinal.value = withContext(IO) {
                cryptocurrenciesRepository.insertCoinDetail(coinDetail)
                cryptocurrenciesRepository.getCoinDetail(coinDetail.coinName)
            }
        }
    }

    private fun insertBids(bids: List<BidsEntity>, coinName: String){
        viewModelScope.launch {
            _bidsList.value =  withContext(IO) {
                cryptocurrenciesRepository.insertBids(bids)
                cryptocurrenciesRepository.getBids(coinName)
            }
        }
    }

    private fun insertAsks(asks: List<AsksEntity>, coinName: String){
        viewModelScope.launch {
            _asksList.value   =  withContext(IO) {
                cryptocurrenciesRepository.insertAsks(asks)
                cryptocurrenciesRepository.getAsks(coinName)
            }
        }
    }
}