package com.example.criptomonedasapp.mvvm.ui.coindetailfragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.criptomonedasapp.domain.GetAsksListUseCase
import com.example.criptomonedasapp.domain.GetBidsListUseCase
import com.example.criptomonedasapp.domain.GetCoinDetailsUseCase
import com.example.criptomonedasapp.domain.model.AsksModelData
import com.example.criptomonedasapp.domain.model.BidsModelData
import com.example.criptomonedasapp.domain.model.CoinDetailModelData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CoinDetailViewModelTest{

    @RelaxedMockK
    private lateinit var detailsUseCase : GetCoinDetailsUseCase
    @RelaxedMockK
    private lateinit var asksUseCase: GetAsksListUseCase
    @RelaxedMockK
    private lateinit var bidsUseCase: GetBidsListUseCase

    private lateinit var viewModel: CoinDetailViewModel


    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        viewModel = CoinDetailViewModel(Dispatchers.Unconfined,detailsUseCase, asksUseCase, bidsUseCase)

    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun coinDetailFinal_returnValueOfGetCoinDetails_ReturnsTrue() = runTest {
        val coin = "btc_mxn"
        val detailModelData = CoinDetailModelData("12","13","btc_mxn", "14", "15", "16","17")

        //Given
        coEvery { detailsUseCase(coin) } returns detailModelData
        //When
        detailsUseCase(coin)
        //Then
        viewModel.coinDetailFinal.value?.equals(detailModelData)?.let { assert(it) }
    }

    @Test
    fun liveDataAsksList_AsksModelList_ReturnsTrue() = runTest {
        val coin = "btc_mxn"
        val asksList = listOf(AsksModelData("btc_mxn", "11", "12"), AsksModelData("btc_mxn", "13", "13"))
         //Given
        coEvery { asksUseCase(coin) } returns asksList
        //When
        asksUseCase(coin)
        //Then
        viewModel.asksList.value?.equals(asksList)?.let { assert(it) }
    }

    @Test
    fun liveDataBidsList_BidsModelList_ReturnsTrue() = runTest {
        val coin = "btc_mxn"
        val bidsList = listOf(BidsModelData("btc_mxn", "11", "12"), BidsModelData("btc_mxn", "13", "13"))

        coEvery { bidsUseCase(coin) } returns bidsList
        bidsUseCase(coin)
        viewModel.bidsList.value?.equals(bidsList)?.let { assert(it) }
    }

}