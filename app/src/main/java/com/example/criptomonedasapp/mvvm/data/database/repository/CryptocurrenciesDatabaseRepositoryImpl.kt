package com.example.criptomonedasapp.mvvm.data.database.repository

import com.example.criptomonedasapp.config.InitialAplication
import com.example.criptomonedasapp.model.CoinsModelCard
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.model.network.typeCoins
import com.example.criptomonedasapp.mvvm.data.database.domain.repository.CryptocurrenciesDatabaseRepository
import com.example.criptomonedasapp.mvvm.data.database.entities.AsksEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.BidsEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinCardEntity
import com.example.criptomonedasapp.mvvm.data.database.entities.CoinDetailEntity

class CryptocurrenciesDatabaseRepositoryImpl: CryptocurrenciesDatabaseRepository {
    private lateinit var coinModelDatabase: CoinDetailModel

    override suspend fun insertAllCoin(coins: CoinCardEntity) {
        InitialAplication.CryptocurrenciesBD.cryptocurrenciesDao().insertAllCoin(
            coins
        )
    }

    override suspend fun getCoinList(): ArrayList<CoinsModelCard> {
        val listCard = InitialAplication.CryptocurrenciesBD.cryptocurrenciesDao().getCoinList()
        val listCoinsObtainedFinal = ArrayList<CoinsModelCard>()

        listCard.forEach {
            listCoinsObtainedFinal.add(
                CoinsModelCard(
                    coinName = typeCoins(it.id).coinName,
                    id = it.id,
                    drawable = typeCoins(it.id).drawable,
                    maxValue = it.maxValue,
                    minValue = it.minValue
                )
            )
        }
        return listCoinsObtainedFinal
    }

    override suspend fun insertCoinDetail(coinDetail: CoinDetailModel) {
        InitialAplication.CryptocurrenciesBD.cryptocurrenciesDao().insertCoinDetail(
            CoinDetailEntity(
                coinName = coinDetail.coinName,
                highValue = coinDetail.highValue,
                lastValue = coinDetail.lastValue,
                volume = coinDetail.volume,
                lowValue = coinDetail.lowValue,
                ask = coinDetail.ask,
                bid = coinDetail.bid
            )
        )
    }

    override suspend fun getCoinDetail(coin: String): CoinDetailModel {
        val coinDetailEntity = InitialAplication.CryptocurrenciesBD.cryptocurrenciesDao().getCoinDetail(coin)

        coinDetailEntity?.let {
            coinModelDatabase = CoinDetailModel(
                highValue = it.highValue,
                lastValue = it.lastValue, coinName = it.coinName, volume = it.volume,
                lowValue = it.lowValue, ask = it.ask, bid = it.bid
            )
            return coinModelDatabase
        }
    }

    override suspend fun insertAsks(coins: List<AsksModel>) {
        coins.forEach {
            InitialAplication.CryptocurrenciesBD.cryptocurrenciesDao().insertAsks(
                AsksEntity(coinName = it.coinName, price = it.price, amount = it.amount)
            )
        }
    }

    override suspend fun getAsks(coin: String): ArrayList<AsksModel> {
        val resultDataAsks = InitialAplication.CryptocurrenciesBD.cryptocurrenciesDao().getAsks(coin)
        val asks = ArrayList<AsksModel>()

        resultDataAsks.forEach {
            asks.add(AsksModel(coinName = it.coinName, price = it.price, amount = it.amount))
        }
        return asks
    }

    override suspend fun insertBids(coins: List<BidsModel>) {
        coins.forEach {
            InitialAplication.CryptocurrenciesBD.cryptocurrenciesDao().insertBids(
                BidsEntity(coinName = it.coinName, price = it.price, amount = it.amount)
            )
        }
    }

    override suspend fun getBids(coin: String): ArrayList<BidsModel> {
        val resultDataBids = InitialAplication.CryptocurrenciesBD.cryptocurrenciesDao().getBids(coin)
        val bids = ArrayList<BidsModel>()

        resultDataBids.forEach {
            bids.add(BidsModel(coinName = it.coinName, price = it.price, amount = it.amount))
        }
        return bids
    }
}