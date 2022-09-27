package com.example.criptomonedasapp.mvvm.iu.coindetailfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesUseCase


class CoinDetailViewModelFactory(val useCase: CryptocurrenciesUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(CoinDetailViewModel::class.java)){
            return CoinDetailViewModel(useCase) as T
        }
        return super.create(modelClass, extras)

    }

}