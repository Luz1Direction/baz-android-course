package com.example.criptomonedasapp.mvvm.iu.listcoinsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesRepositoryImpl
import com.example.criptomonedasapp.model.CoinsModelCard
import com.example.criptomonedasapp.model.network.CoinListModel
import com.example.criptomonedasapp.mvvm.adapter.ListCoinAdapter
import com.example.criptomonedasapp.mvvm.interfaces.CoinDetailResultCallback
import com.example.criptomonedasapp.utils.GetCoinCardModel.getCoinIcon
import com.example.criptomonedasapp.utils.GetCoinCardModel.getNameCoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinListViewModel : ViewModel() {

    var  repository : CryptocurrenciesRepositoryImpl = CryptocurrenciesRepositoryImpl()

    //lateinit var r : CryptocurrenciesUseCase
    private lateinit var list: List<CoinListModel>
    var listCoinsObtenied= ArrayList<CoinsModelCard>()

    var adapter = MutableLiveData<ListCoinAdapter>()

    fun getAll(context: CoinDetailResultCallback) {
        viewModelScope.launch {
            list = withContext(Dispatchers.IO){
                repository.getCoinList()
            }
            if(list.isNotEmpty()){
                list.forEach {
                    if(it.coinName.contains("mxn")){
                        listCoinsObtenied.add(CoinsModelCard(coinName = getNameCoin(it.coinName), id = it.coinName,
                            drawable = getCoinIcon(it.coinName), maxValue = it.maximum_value, minValue = it.minimum_value ))
                    }
                }
                adapter.value = ListCoinAdapter(listCoinsObtenied, context)
            }

        }
    }





}