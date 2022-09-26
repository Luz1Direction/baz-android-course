package com.example.criptomonedasapp.model.network



data class FeesModel(
    var flat_rate: FlatRateModel,
    var structure: List<StructureModel>,
)
