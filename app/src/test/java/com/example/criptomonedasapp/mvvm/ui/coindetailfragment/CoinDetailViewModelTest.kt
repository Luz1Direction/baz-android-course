package com.example.criptomonedasapp.mvvm.ui.coindetailfragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.criptomonedasapp.model.network.AsksAndBidsModel
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.mvvm.data.database.usecases.CryptocurrenciesDataSourceUseCase
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesRemoteDataSourceUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CoinDetailViewModelTest{

    @RelaxedMockK
    private lateinit var useCase : CryptocurrenciesRemoteDataSourceUseCase
    @RelaxedMockK
    private lateinit var  useCaseDatabase : CryptocurrenciesDataSourceUseCase

    private lateinit var viewModel: CoinDetailViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModel = CoinDetailViewModel(useCase, useCaseDatabase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `Get CoinDetailModel when the viewmodel is created`() = runTest {
        val coin = "btc_mxn"
        val detailModel = CoinDetailModel("12","13","btc_mxn", "14", "15", "16","17")

        //Given
        coEvery { useCase.getCoinDetails(coin) } returns detailModel
        //When
        useCase.getAsksAndBids(coin)
        //Then
        viewModel.coinDetailFinal.value?.equals(detailModel)?.let { assert(it) }
    }

    @Test
    fun `Get the list of asks when the viewmodel is created`() = runTest {
        val coin = "btc_mxn"
        val asksList = listOf(AsksModel("btc_mxn", "11", "12"), AsksModel("btc_mxn", "13", "13"))
        val bidsList = listOf(BidsModel("btc_mxn", "11", "12"), BidsModel("btc_mxn", "13", "13"))
        val asksAndBids = AsksAndBidsModel(asksList,bidsList)
        //Given
        coEvery { useCase.getAsksAndBids(coin) } returns asksAndBids
        //When
        useCase.getAsksAndBids(coin)
        //Then
        viewModel.asksList.value?.equals(asksList)?.let { assert(it) }
    }

    @Test
    fun `Get the list of bids when the viewmodel is created`() = runTest {
        val coin = "btc_mxn"
        val asksList = listOf(AsksModel("btc_mxn", "11", "12"), AsksModel("btc_mxn", "13", "13"))
        val bidsList = listOf(BidsModel("btc_mxn", "11", "12"), BidsModel("btc_mxn", "13", "13"))
        val asksAndBids = AsksAndBidsModel(asksList,bidsList)

        coEvery { useCase.getAsksAndBids(coin) } returns asksAndBids
        useCase.getAsksAndBids(coin)
        viewModel.bidsList.value?.equals(bidsList)?.let { assert(it) }
    }

}