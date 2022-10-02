package com.example.criptomonedasapp.mvvm.data.database.repository

import com.example.criptomonedasapp.model.CoinCardModel
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.model.network.getCoinModel
import com.example.criptomonedasapp.mvvm.data.database.CoinsDatabase
import com.example.criptomonedasapp.mvvm.data.database.domain.repository.CryptocurrenciesDataSource
import com.example.criptomonedasapp.mvvm.data.database.entities.AsksEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.BidsEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinDetailEntity
import javax.inject.Inject

class CryptocurrenciesDataSourceImpl @Inject constructor(
    private val cryptocurrenciesBD : CoinsDatabase
): CryptocurrenciesDataSource {

    override suspend fun insertAllCoin(coins: CoinCardEntity) {
        cryptocurrenciesBD.cryptocurrenciesDao().insertAllCoin(coins)
    }

    override suspend fun getCoinList(): ArrayList<CoinCardModel> {
        val listCard = cryptocurrenciesBD.cryptocurrenciesDao().getCoinList()
        val listCoinsObtainedFinal = ArrayList<CoinCardModel>()

        listCard.forEach {
            listCoinsObtainedFinal.add(
                CoinCardModel(
                    coinName = getCoinModel(it.id).coinName,
                    id = it.id,
                    drawable = getCoinModel(it.id).drawable,
                    maxValue = it.maxValue,
                    minValue = it.minValue
                )
            )
        }
        return listCoinsObtainedFinal
    }

    override suspend fun insertCoinDetail(coinDetail: CoinDetailEntity) {
        cryptocurrenciesBD.cryptocurrenciesDao().insertCoinDetail(
            coinDetail
        )
    }

    override suspend fun getCoinDetail(coin: String): CoinDetailModel {
        val coinDetailModel = cryptocurrenciesBD.cryptocurrenciesDao().getCoinDetail(coin)

        return CoinDetailModel(coinDetailModel.highValue,
                coinDetailModel.lastValue,
                coinDetailModel.coinName,
                coinDetailModel.volume,
                coinDetailModel.lowValue,
                coinDetailModel.ask,
                coinDetailModel.bid)
    }

    override suspend fun insertAsks(coins: List<AsksEntity>) {
        coins.forEach {
            cryptocurrenciesBD.cryptocurrenciesDao().insertAsks(it)
        }
    }

    override suspend fun getAsks(coin: String): ArrayList<AsksModel> {
        val resultDataAsks = cryptocurrenciesBD.cryptocurrenciesDao().getAsks(coin)
        val asks = ArrayList<AsksModel>()

        resultDataAsks.forEach {
            asks.add(AsksModel(coinName = it.coinName, price = it.price, amount = it.amount))
        }
        return asks
    }

    override suspend fun insertBids(coins: List<BidsModel>) {
        coins.forEach {
            cryptocurrenciesBD.cryptocurrenciesDao().insertBids(
                BidsEntity(coinName = it.coinName, price = it.price, amount = it.amount)
            )
        }
    }

    override suspend fun getBids(coin: String): ArrayList<BidsModel> {
        val resultDataBids = cryptocurrenciesBD.cryptocurrenciesDao().getBids(coin)
        val bids = ArrayList<BidsModel>()

        resultDataBids.forEach {
            bids.add(BidsModel(coinName = it.coinName, price = it.price, amount = it.amount))
        }
        return bids
    }
}