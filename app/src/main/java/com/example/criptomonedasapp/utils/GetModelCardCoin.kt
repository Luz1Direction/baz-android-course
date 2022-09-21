package com.example.criptomonedasapp.utils

import com.example.criptomonedasapp.model.CoinsModelAux

object GetModelCardCoin {
    var model: DataToCoinsModelAux = DataToCoinsModelAux()

    fun getIconCoin(nameCoin:String):Int{
        return getDataCoins(nameCoin).drawable
    }

    fun getNameCoin(nameCoin:String):String{
        return getDataCoins(nameCoin).nameCoin
    }

    fun getDataCoins(typeCoins:String): CoinsModelAux {
        return model.typeCoins(typeCoins)
    }
}