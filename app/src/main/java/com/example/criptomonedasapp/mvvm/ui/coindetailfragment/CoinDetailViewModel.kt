package com.example.criptomonedasapp.mvvm.ui.coindetailfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.domain.GetAsksListUseCase
import com.example.criptomonedasapp.domain.GetCoinDetailsUseCase
import com.example.criptomonedasapp.domain.GetBidsListUseCase
import com.example.criptomonedasapp.domain.model.AsksModelData
import com.example.criptomonedasapp.domain.model.BidsModelData
import com.example.criptomonedasapp.domain.model.CoinDetailModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private var detailsUseCase : GetCoinDetailsUseCase,
    private var asksUseCase: GetAsksListUseCase,
    private var bidsUseCase: GetBidsListUseCase
    ) : ViewModel() {

    private var _bidsList = MutableLiveData<List<BidsModelData>?>()
    var bidsList: MutableLiveData<List<BidsModelData>?> = _bidsList

    private var _asksList = MutableLiveData<List<AsksModelData>?>()
    var asksList: MutableLiveData<List<AsksModelData>?> = _asksList

    private var _coinDetailFinal = MutableLiveData<CoinDetailModelData?>()
    var coinDetailFinal: MutableLiveData<CoinDetailModelData?> = _coinDetailFinal

    fun getCoinDetail(nameCoin: String) {
        viewModelScope.launch {
            _coinDetailFinal.value = withContext(dispatcher) {
                detailsUseCase(nameCoin)
            }
        }
    }

    fun getAllAsks(coin: String) {
        viewModelScope.launch {
            _asksList.value = withContext(dispatcher) {
                asksUseCase(coin)
            }
        }
    }

    fun getAllBids(coin: String) {
        viewModelScope.launch {
            _bidsList.value = withContext(dispatcher) {
                bidsUseCase(coin)
            }
        }
    }
}