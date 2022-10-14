package com.example.criptomonedasapp.domain.usecases

import com.example.criptomonedasapp.domain.GetCoinDetailsUseCase
import com.example.criptomonedasapp.data.network.model.network.*
import com.example.criptomonedasapp.data.network.CryptocurrenciesRemoteDataSource
import com.example.criptomonedasapp.domain.model.CoinDetailModelData
import com.example.criptomonedasapp.domain.repository.CryptocurrenciesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCoinDetailsUseCaseTest{


    @RelaxedMockK
    private lateinit var repository: CryptocurrenciesRepository

    lateinit var useCase: GetCoinDetailsUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        useCase = GetCoinDetailsUseCase(repository)
    }

    @Test
    fun `when the getCoinDetailsFromApi function is called, it must return a CoinDetailModelData`() = runBlocking{
        val coin = "btc_mxn"
        val coinDetailModelData = CoinDetailModelData("11", "12", "btc_mxn", "13", "14", "15", "16")
        //Given
        coEvery { repository.getCoinDetailsFromApi(coin) } returns coinDetailModelData

        //When
        val result = useCase(coin)

        //Then
        assert(coinDetailModelData == result)
    }


}