package com.example.criptomonedasapp.utils

import com.example.criptomonedasapp.model.CoinAuxiliaryModel

object GetCoinCardModel {
    var model: DataToCoinsModelAux = DataToCoinsModelAux()

    fun getCoinIcon(nameCoin:String):Int{
        return getDataCoins(nameCoin).drawable
    }

    fun getNameCoin(nameCoin:String):String{
        return getDataCoins(nameCoin).coinName
    }

    fun getDataCoins(typeCoins:String): CoinAuxiliaryModel {
        return model.typeCoins(typeCoins)
    }
}