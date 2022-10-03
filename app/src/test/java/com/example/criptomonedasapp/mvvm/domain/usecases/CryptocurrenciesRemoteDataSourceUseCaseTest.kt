package com.example.criptomonedasapp.mvvm.domain.usecases

import com.example.criptomonedasapp.model.network.*
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CryptocurrenciesRemoteDataSourceUseCaseTest{


    @RelaxedMockK
    private lateinit var repository: CryptocurrenciesRemoteDataSource

    lateinit var useCase: CryptocurrenciesRemoteDataSourceUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        useCase = CryptocurrenciesRemoteDataSourceUseCase(repository)
    }

    @Test
    fun `when the getCoinDetails function is called, it must return a CoinDetailModel`() = runBlocking{
        val coin = "btc_mxn"
        val coinDetailModel = CoinDetailModel("11", "12", "btc_mxn", "13", "14", "15", "16")
        //Given
        coEvery { repository.getCoinDetails(coin) } returns coinDetailModel

        //When
        val result = useCase.getCoinDetails(coin)

        //Then
        assert(coinDetailModel == result)
    }

    @Test
    fun `when the getAsksAndBids function is called, it must return an AsksAndBidsModel`() = runBlocking{
        val coin = "btc_mxn"
        val asksList = listOf(AsksModel("btc_mxn", "11", "12"), AsksModel("btc_mxn", "13", "13"))
        val bidsList = listOf(BidsModel("btc_mxn", "11", "12"), BidsModel("btc_mxn", "13", "13"))
        val asksAndBids = AsksAndBidsModel(asksList,bidsList)

        //Given
        coEvery { repository.getAsksAndBids(coin) } returns asksAndBids

        //When
        val result = useCase.getAsksAndBids(coin)

        //Then
        assert(asksAndBids == result)
    }


}