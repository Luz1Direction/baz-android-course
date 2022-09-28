package com.example.criptomonedasapp.mvvm.domain.usecases

import com.example.criptomonedasapp.model.network.*
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CryptocurrenciesUseCaseTest{

    @RelaxedMockK
    private lateinit var repository: CryptocurrenciesRepository

    lateinit var useCase: CryptocurrenciesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        useCase = CryptocurrenciesUseCase(repository)
    }

    @Test
    fun `cuando se mande a llamar a la funcion getAvailableCoinList debe retornar un List de CoinListModel`() = runBlocking{
        val myList = listOf(CoinListModel("btc_mxn", "11", "12", "13", "14", "15","16", "17"),
            CoinListModel("eth_mxn", "21", "22", "23", "24", "25","26", "27")
        )
        //Given
        coEvery { repository.getCoinList() } returns myList

        //When
        val result = useCase.getAvailableCoinList()

        //Then
        assert(myList == result)
    }

    @Test
    fun `cuando se mande a llamar a la funcion getCoinDetails debe retornar un CoinDetailModel`() = runBlocking{
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
    fun `cuando se mande a llamar a la funcion getAsksAndBids debe retornar un AsksAndBidsModel`() = runBlocking{
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