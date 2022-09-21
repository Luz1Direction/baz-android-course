package com.example.criptomonedasapp.utils

import com.example.criptomonedasapp.R
import com.example.criptomonedasapp.model.CoinsModelAux


class DataToCoinsModelAux {
    fun typeCoins(typeCoin: String): CoinsModelAux {
        return when (typeCoin) {
            "btc_mxn" -> CoinsModelAux(nameCoin = "BitCoin", drawable = R.drawable.img_bitcoin)
            "eth_mxn" -> CoinsModelAux(nameCoin = "Ethereum", drawable = R.drawable.img_ethereum)
            "xrp_mxn" -> CoinsModelAux(nameCoin = "XRP", drawable = R.drawable.img_xrp)
            "ltc_mxn" -> CoinsModelAux(nameCoin = "LiteCoin", drawable = R.drawable.img_liteicon)
            "bch_mxn" -> CoinsModelAux(nameCoin = "Bitcoin Cash", drawable = R.drawable.img_bitcoin_cash)
            "tusd_mxn" -> CoinsModelAux(nameCoin = "TrueUSD", drawable = R.drawable.img_tusd)
            "mana_mxn" -> CoinsModelAux(nameCoin = "Decentraland", drawable = R.drawable.img_decentraland)
            "bat_mxn" -> CoinsModelAux(nameCoin = "Bat Finance", drawable = R.drawable.img_bat_finance)
            "dai_mxn" -> CoinsModelAux(nameCoin = "DAI", drawable = R.drawable.img_dai)
            "usd_mxn" -> CoinsModelAux(nameCoin = "USD+", drawable = R.drawable.img_usd)
            else -> CoinsModelAux(nameCoin = "Crypto", drawable = R.drawable.img_bitcoin)
        }
    }

}
