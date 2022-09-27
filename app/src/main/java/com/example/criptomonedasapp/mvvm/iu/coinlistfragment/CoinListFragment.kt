package com.example.criptomonedas.mvvm.listcoinsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criptomonedasapp.R
import com.example.criptomonedasapp.databinding.CoinListFragmentBinding
import com.example.criptomonedasapp.mvvm.adapter.CoinListAdapter
import com.example.criptomonedasapp.mvvm.data.repository.CryptocurrenciesRepositoryImpl
import com.example.criptomonedasapp.mvvm.domain.repository.CryptocurrenciesRepository
import com.example.criptomonedasapp.mvvm.domain.usecases.CryptocurrenciesUseCase
import com.example.criptomonedasapp.mvvm.interfaces.CoinDetailResultCallback
import com.example.criptomonedasapp.mvvm.iu.coinlistfragment.CoinListViewModel
import com.example.criptomonedasapp.mvvm.iu.coinlistfragment.CoinListViewModelFactory

class CoinListFragment() : Fragment(), CoinDetailResultCallback {

    private var _binding: CoinListFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CoinListViewModel by viewModels {
        var repository = CryptocurrenciesRepositoryImpl()
        var useCase = CryptocurrenciesUseCase(repository)
        CoinListViewModelFactory(useCase)}

    private val coinListAdapter: CoinListAdapter by lazy {
        CoinListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoinListFragmentBinding.inflate(inflater, container, false)

        viewModel.getAllCoins()
        binding.coinListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.coinListRecyclerView.adapter = coinListAdapter
        viewModel.coinList.observe(viewLifecycleOwner) {
            coinListAdapter.submitList(it)
        }
        return binding.root
    }

    override fun goCoinDetail(coin: String) {
        setFragmentResult("requestKey", bundleOf("coinNameKey" to coin))
        findNavController().navigate(R.id.action_listCoinsFragment_to_coinDetailFragment2)
    }
}