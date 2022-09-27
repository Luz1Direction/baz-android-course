package com.example.criptomonedasapp.mvvm.iu.coinlistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesUseCase


class CoinListViewModelFactory(val useCase: CryptocurrenciesUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(CoinListViewModel::class.java)){
            return CoinListViewModel(useCase) as T
        }
        return super.create(modelClass, extras)

    }

}