package com.example.criptomonedasapp.mvvm.iu.coindetailfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criptomonedasapp.databinding.CoinDetailFragmentBinding
import com.example.criptomonedasapp.model.network.typeCoins
import com.example.criptomonedasapp.mvvm.adapter.AsksAdapter
import com.example.criptomonedasapp.mvvm.adapter.BidsAdapter
import com.example.criptomonedasapp.mvvm.data.database.repository.CryptocurrenciesDatabaseRepositoryImpl
import com.example.criptomonedasapp.mvvm.data.database.usecases.CryptocurrenciesDatabaseUseCase
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesRepositoryImpl
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesUseCase

class CoinDetailFragment : Fragment(){
    private var _binding: CoinDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CoinDetailViewModel by viewModels {
        val repositoryDatabase = CryptocurrenciesDatabaseRepositoryImpl()
        val useCaseDatabase = CryptocurrenciesDatabaseUseCase(repositoryDatabase)
        val repository = CryptocurrenciesRepositoryImpl()
        val useCase = CryptocurrenciesUseCase(repository)
        CoinDetailViewModelFactory(useCase, useCaseDatabase)
    }
    private val bidsAdapter: BidsAdapter by lazy { BidsAdapter() }
    private val asksAdapter: AsksAdapter by lazy { AsksAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoinDetailFragmentBinding.inflate(inflater, container, false)

        setFragmentResultListener("requestKey") { key, bundle ->
            val resultCoinName = bundle.getString("coinNameKey")
            resultCoinName?.let {
                viewModel.getCoinDetail(resultCoinName)
                viewModel.getAsksAndBids(resultCoinName)
            }
        }

        viewModel.coinDetailFinal.observe(viewLifecycleOwner) {
            with(binding){
                highCoinTxt.text = it.highValue
                lastCoinTxt.text = it.lastValue
                lowCoinTxt.text = it.lowValue
                coinNameTxt.text = typeCoins(it.coinName).coinName
                coinIconImg.setImageResource(typeCoins(it.coinName).drawable) }
        }

        binding.bidssListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.bidssListRecyclerView.adapter = bidsAdapter
        viewModel.bidsList.observe(viewLifecycleOwner) {
            bidsAdapter.submitList(it)
        }

        binding.asksListRecyclerView.adapter = asksAdapter
        binding.asksListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.asksList.observe(viewLifecycleOwner) {
            asksAdapter.submitList(it)
        }
        return binding.root
    }
}