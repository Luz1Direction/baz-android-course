package com.example.criptomonedasapp.mvvm.ui.coinlistfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.model.CoinCardModel
import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.model.network.getCoinModel
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity
import com.example.criptomonedasapp.mvvm.data.database.usecases.CryptocurrenciesDataSourceUseCase
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesRemoteDataSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val useCase : CryptocurrenciesRemoteDataSourceUseCase,
    private val useCaseDatabase : CryptocurrenciesDataSourceUseCase
    ) : ViewModel() {

    private var _coinListFinal = MutableLiveData<List<CoinCardModel>?>()
    var coinFinalList: MutableLiveData<List<CoinCardModel>?> = _coinListFinal

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
        viewModelScope.launch {
            val coinObtainedList = ArrayList<CoinCardEntity>()
            val allCoinsList = withContext(Dispatchers.IO) {
                useCase.getAvailableCoinList()
            }?.filter {  it.coinName.contains("mxn")}

            if (allCoinsList != null) {
                  allCoinsList.forEach {
                            coinObtainedList.add(
                                CoinCardEntity(
                                    coinName = getCoinModel(it.coinName).coinName,
                                    id = it.coinName,
                                    drawable = getCoinModel(it.coinName).drawable,
                                    maxValue = it.maximumValue,
                                    minValue = it.minimumValue
                                )
                            )
                        }
                insertCoinListDatabase(coinObtainedList)
            }
        }
    }

    private fun insertCoinListDatabase(coinList: List<CoinCardEntity>) {
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