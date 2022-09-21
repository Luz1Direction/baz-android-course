package com.example.criptomonedasapp.model.response

import com.google.gson.annotations.SerializedName

data class CoinsResponse (
    @SerializedName("success") var success: String,
    @SerializedName("payload") var payload: List<Payload>
)

data class Payload(
    @SerializedName("book") var coinName: String,
    @SerializedName("minimum_price") var minimum_price: String,
    @SerializedName("maximum_price") var maximum_price: String,
    @SerializedName("minimum_amount") var minimum_amount: String,
    @SerializedName("maximum_amount") var maximum_amount: String,
    @SerializedName("minimum_value") var minimum_value: String,
    @SerializedName("maximum_value") var maximum_value: String,
    @SerializedName("tick_size") var tick_size: String,
    @SerializedName("default_chart") var default_chart: String,
    @SerializedName("fees") var fees: FeesModel
)

data class FeesModel(
    @SerializedName("flat_rate") var flat_rate: FlatRateModel,
    @SerializedName("structure") var structure: List<StructureModel>,
)

data class FlatRateModel(
    @SerializedName("maker") var maker: String,
    @SerializedName("taker") var taker: String,
)

data class StructureModel(
    @SerializedName("volume") var volume :String,
    @SerializedName("maker") var maker :String,
    @SerializedName("taker") var taker :String,

    )
