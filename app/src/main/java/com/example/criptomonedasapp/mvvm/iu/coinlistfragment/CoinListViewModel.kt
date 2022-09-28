package com.example.criptomonedasapp.mvvm.iu.coinlistfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.model.CoinsModelCard
import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.model.network.typeCoins
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity
import com.example.criptomonedasapp.mvvm.data.database.usecases.CryptocurrenciesDatabaseUseCase
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinListViewModel(var useCase : CryptocurrenciesUseCase, var useCaseDatabase : CryptocurrenciesDatabaseUseCase) : ViewModel() {

    private lateinit var listAllCoins: List<CoinListModel>
    private lateinit var listCoinModelCard: ArrayList<CoinsModelCard>

    private var listCoinsObtained = ArrayList<CoinsModelCard>()

    private var _coinListFinal = MutableLiveData<List<CoinsModelCard>>()
    var coinListFinal: LiveData<List<CoinsModelCard>> = _coinListFinal

    fun getAllCoins() {
        viewModelScope.launch {
            listAllCoins = withContext(Dispatchers.IO) {
                useCase.getAvailableCoinList()
            }
            if (listAllCoins.isNotEmpty()) {
                listAllCoins.forEach {
                    if (it.coinName.contains("mxn")) {
                        listCoinsObtained.add(
                            CoinsModelCard(
                                coinName = typeCoins(it.coinName).coinName,
                                id = it.coinName,
                                drawable = typeCoins(it.coinName).drawable,
                                maxValue = it.maximum_value,
                                minValue = it.minimum_value
                            )
                        )
                    }
                }
                insertCoinListDatabase(listCoinsObtained)
            } else {
                getAllCoinsDatabase()
            }
        }
    }

    private fun insertCoinListDatabase(coinList: List<CoinsModelCard>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coinList.forEach {
                    useCaseDatabase.insertAllCoin(
                        CoinCardEntity(
                            id = it.id,
                            coinName = it.coinName,
                            maxValue = it.maxValue,
                            minValue = it.minValue,
                            drawable = it.drawable
                        )
                    )
                }
                getAllCoinsDatabase()
            }
        }
    }

    private fun getAllCoinsDatabase() {
        viewModelScope.launch {
            listCoinModelCard = withContext(Dispatchers.IO) {
                useCaseDatabase.getCoinList()
            }
            _coinListFinal.value = listCoinModelCard
        }
    }
}