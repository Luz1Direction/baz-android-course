package com.example.criptomonedasapp.mvvm.ui.coindetailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criptomonedasapp.databinding.CoinDetailFragmentBinding
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.model.network.CoinDetailModel
import com.example.criptomonedasapp.model.network.getCoinModel
import com.example.criptomonedasapp.mvvm.adapter.AsksAdapter
import com.example.criptomonedasapp.mvvm.adapter.BidsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment(){
    private var _binding: CoinDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CoinDetailViewModel by viewModels()
    private val bidsAdapter: BidsAdapter by lazy { BidsAdapter() }
    private val asksAdapter: AsksAdapter by lazy { AsksAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoinDetailFragmentBinding.inflate(inflater, container, false)

        getCoinNameFromCoinListFragment()

        viewModel.coinDetailFinal.observe(viewLifecycleOwner) {
            setUp(it)
        }

        binding.bidssListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.bidssListRecyclerView.adapter = bidsAdapter
        viewModel.bidsList.observe(viewLifecycleOwner) {
            onBids(it)
        }

        binding.asksListRecyclerView.adapter = asksAdapter
        binding.asksListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.asksList.observe(viewLifecycleOwner) {
            onAsks(it)
        }
        return binding.root
    }

    private fun getCoinNameFromCoinListFragment() {
        setFragmentResultListener("requestKey") { _, bundle ->
            val resultCoinName = bundle.getString("coinNameKey")
            resultCoinName?.let {
                        viewModel.getAsksAndBids(resultCoinName)
                        viewModel.getCoinDetail(resultCoinName)
            }
        }
    }

    private fun setUp(coin: CoinDetailModel?) {
        with(binding) {
            coin?.let {
                highCoinTxt.text = it.highValue
                lastCoinTxt.text = it.lastValue
                lowCoinTxt.text = it.lowValue
                coinNameTxt.text = getCoinModel(it.coinName).coinName
                coinIconImg.setImageResource(getCoinModel(it.coinName).drawable)
            }
        }
    }

    private fun onAsks(asks: List<AsksModel>?) {
        asks?.let {
            asksAdapter.submitList(it)
        }
    }

    private fun onBids(bids: List<BidsModel>?) {
        bids?.let {
            bidsAdapter.submitList(it)
        }
    }
}