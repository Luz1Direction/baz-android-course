package com.example.criptomonedasapp.mvvm.listcoinsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.dao.ListCoinsDao
import com.example.criptomonedasapp.model.CoinsModelAux
import com.example.criptomonedasapp.model.CoinsModelCard
import com.example.criptomonedasapp.model.response.Payload
import com.example.criptomonedasapp.mvvm.adapter.ListCoinAdapter
import com.example.criptomonedasapp.mvvm.interfaces.DetailCoinResultCallback
import com.example.criptomonedasapp.utils.DataToCoinsModelAux
import com.example.criptomonedasapp.utils.GetModelCardCoin.getIconCoin
import com.example.criptomonedasapp.utils.GetModelCardCoin.getNameCoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListCoinsViewModel : ViewModel() {

    var  repository : ListCoinsDao = ListCoinsDao()
    private lateinit var list: List<Payload>
    var listCoinsObtenied= ArrayList<CoinsModelCard>()

    var adapter = MutableLiveData<ListCoinAdapter>()


    fun getAll(context: DetailCoinResultCallback) {
        viewModelScope.launch {
            list = withContext(Dispatchers.IO){
                repository.getListCoins()
            }
            if(list.isNotEmpty()){
                list.forEach {
                    if(it.coinName.contains("mxn")){
                        listCoinsObtenied.add(CoinsModelCard(nameCoin = getNameCoin(it.coinName), id = it.coinName,
                            drawable = getIconCoin(it.coinName), maxValue = it.maximum_value, minValue = it.minimum_value ))
                    }
                }
                adapter.value = ListCoinAdapter(listCoinsObtenied, context)
            }

        }
    }





}