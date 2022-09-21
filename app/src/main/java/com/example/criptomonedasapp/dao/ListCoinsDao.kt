package com.example.criptomonedasapp.dao

import com.example.criptomonedasapp.config.InitialAplication.Companion.webService
import com.example.criptomonedasapp.model.DetailsCoinModel

import com.example.criptomonedasapp.model.response.Payload

class ListCoinsDao {

    lateinit var list: List<Payload>
    lateinit var details: DetailsCoinModel


fun getListCoins(): List<Payload> {
        var callRespuesta =  webService.getCoins()
        var listObtained = callRespuesta.execute()

        if (listObtained.isSuccessful) {
            listObtained?.body()?.let {
                list = it.payload
            }
        }
        return list
    }


    fun getDetailsCoins(id:String): DetailsCoinModel{
        var callRespuesta =  webService.getDetailsCoin(id)
        var listObtained = callRespuesta.execute()

        if (listObtained.isSuccessful) {
            listObtained?.body()?.let {
                details = it.detailsCoin
            }
        }

        return details

    }

}

