package com.example.criptomonedasapp.mvvm.detailscoinfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.dao.ListCoinsDao
import com.example.criptomonedasapp.model.DetailsCoinModel
import com.example.criptomonedasapp.model.response.Payload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailViewModel : ViewModel() {

    var  repository : ListCoinsDao = ListCoinsDao()
    private lateinit var list: DetailsCoinModel



    private val _detail = MutableLiveData<DetailsCoinModel>()
    val detail: LiveData<DetailsCoinModel> = _detail

    fun getDetailCoin(nameCoin: String) {
        viewModelScope.launch {
            list = withContext(Dispatchers.IO) {
                repository.getDetailsCoins(nameCoin)
            }
            _detail.value = list
        }
    }


}