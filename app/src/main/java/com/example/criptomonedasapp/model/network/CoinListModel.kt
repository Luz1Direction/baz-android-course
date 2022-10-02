package com.example.criptomonedasapp.model.network

import com.example.criptomonedasapp.R
import com.example.criptomonedasapp.model.CoinAuxiliaryModel
import com.google.gson.annotations.SerializedName

data class CoinListModel(
    @SerializedName("book") val coinName: String,
    @SerializedName("minimum_price") val minimumPrice: String,
    @SerializedName("maximum_price") val maximumPrice: String,
    @SerializedName("minimum_amount") val minimumAmount: String,
    @SerializedName("maximum_amount") val maximumAmount: String,
    @SerializedName("minimum_value")val minimumValue: String,
    @SerializedName("maximum_value") val maximumValue: String,
    @SerializedName("tick_size") val tickSize: String
)

fun getCoinModel(typeCoin: String): CoinAuxiliaryModel {
    return when (typeCoin) {
        "btc_mxn" -> CoinAuxiliaryModel(coinName = "BitCoin", drawable = R.drawable.img_bitcoin)
        "eth_mxn" -> CoinAuxiliaryModel(coinName = "Ethereum", drawable = R.drawable.img_ethereum)
        "xrp_mxn" -> CoinAuxiliaryModel(coinName = "XRP", drawable = R.drawable.img_xrp)
        "ltc_mxn" -> CoinAuxiliaryModel(coinName = "LiteCoin", drawable = R.drawable.img_liteicon)
        "bch_mxn" -> CoinAuxiliaryModel(coinName = "BitcoinCash", drawable = R.drawable.img_bitcoin_cash)
        "tusd_mxn" -> CoinAuxiliaryModel(coinName = "TrueUSD", drawable = R.drawable.img_tusd)
        "mana_mxn" -> CoinAuxiliaryModel(coinName = "Decentraland", drawable = R.drawable.img_decentraland)
        "bat_mxn" -> CoinAuxiliaryModel(coinName = "Bat Finance", drawable = R.drawable.img_bat_finance)
        "dai_mxn" -> CoinAuxiliaryModel(coinName = "DAI", drawable = R.drawable.img_dai)
        "usd_mxn" -> CoinAuxiliaryModel(coinName = "USD+", drawable = R.drawable.img_usd)
        else -> CoinAuxiliaryModel(coinName = "Crypto", drawable = R.drawable.img_bitcoin)
    }
}
