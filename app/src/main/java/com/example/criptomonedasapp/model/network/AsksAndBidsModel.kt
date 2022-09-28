package com.example.criptomonedasapp.model.network

data class AsksAndBidsModel(
    val asks : List<AsksModel> = arrayListOf(),
    val bids : List<BidsModel> = arrayListOf()
)


