package com.example.criptomonedasapp.mvvm.iu.coinlistfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesRepositoryImpl
import com.example.criptomonedasapp.model.CoinsModelCard
import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.utils.GetCoinCardModel.getCoinIcon
import com.example.criptomonedasapp.utils.GetCoinCardModel.getNameCoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinListViewModel : ViewModel() {

    var repository: CryptocurrenciesRepositoryImpl = CryptocurrenciesRepositoryImpl()

    private lateinit var list: List<CoinListModel>
    private var listCoinsObtenied = ArrayList<CoinsModelCard>()

    private var _coinList = MutableLiveData<List<CoinsModelCard>>()
    var coinList: LiveData<List<CoinsModelCard>> = _coinList

    fun getAllCoins() {
        viewModelScope.launch {
            list = withContext(Dispatchers.IO) {
                repository.getCoinList()
            }
            if (list.isNotEmpty()) {
                list.forEach {
                    if (it.coinName.contains("mxn")) {
                        listCoinsObtenied.add(
                            CoinsModelCard(
                                coinName = getNameCoin(it.coinName),
                                id = it.coinName,
                                drawable = getCoinIcon(it.coinName),
                                maxValue = it.maximum_value,
                                minValue = it.minimum_value
                            )
                        )
                    }
                }
                _coinList.value = listCoinsObtenied
            }
        }
    }
}