package com.example.criptomonedasapp.model.network



data class FeesModel(
    val flat_rate: FlatRateModel,
    val structure: List<StructureModel>,
)
