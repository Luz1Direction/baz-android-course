package com.example.criptomonedasapp.mvvm.ui.coinlistfragment

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptomonedasapp.data.network.model.network.response.CoinResponseModel
import com.example.criptomonedasapp.data.network.model.toDomain
import com.example.criptomonedasapp.data.network.services.APIService
import com.example.criptomonedasapp.domain.GetCoinListUseCase
import com.example.criptomonedasapp.domain.model.CoinCardData
import com.example.criptomonedasapp.domain.model.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@VisibleForTesting
@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apiService: APIService,
    private val CoinListDataSourceUseCase : GetCoinListUseCase
    ) : ViewModel() {

    private var _coinListFinal = MutableLiveData<List<CoinCardData>?>()
    var coinFinalList: MutableLiveData<List<CoinCardData>?> = _coinListFinal

    private val _coinListObserve = MutableLiveData<List<CoinCardData>>()
    val coinListObserve: MutableLiveData<List<CoinCardData>> = _coinListObserve

   fun insertAllCoin() {
        apiService.getCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onSuccess: CoinResponseModel?, onError: Throwable? ->
                onSuccess?.let { list ->
                    _coinListObserve.value = list.coinList.filter { coinModel ->
                        coinModel.coinName.contains("mxn")}.map { it.toDomain()
                    }.map { it.toDomain() }
                }
                onError?.let {
                    _coinListObserve.value = emptyList()
                }
            }
    }

    fun insertCoinListDatabase(coinList: List<CoinCardData>) {
        viewModelScope.launch {
            _coinListFinal.value = withContext(dispatcher){
                CoinListDataSourceUseCase(coinList)
            }
        }
    }
}