package com.example.criptomonedasapp.model.response

import com.google.gson.annotations.SerializedName

data class CoinsResponse (
    var success: String,
    @SerializedName("payload") var CoinList: List<CoinList>
)

data class CoinList(
    @SerializedName("book") var coinName: String,
    var minimum_price: String,
    var maximum_price: String,
    var minimum_amount: String,
    var maximum_amount: String,
    var minimum_value: String,
    var maximum_value: String,
    var tick_size: String,
    var default_chart: String,
    var fees: FeesModel
)

data class FeesModel(
    var flat_rate: FlatRateModel,
    var structure: List<StructureModel>,
)

data class FlatRateModel(
    var maker: String,
    var taker: String,
)

data class StructureModel(
    var volume :String,
    var maker :String,
    var taker :String,

    )
