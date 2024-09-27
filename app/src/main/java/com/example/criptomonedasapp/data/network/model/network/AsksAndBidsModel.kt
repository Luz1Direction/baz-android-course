package com.example.criptomonedasapp.data.network.model.network

data class AsksAndBidsModel(
    val asks : List<AsksModel> = arrayListOf(),
    val bids : List<BidsModel> = arrayListOf()
)


