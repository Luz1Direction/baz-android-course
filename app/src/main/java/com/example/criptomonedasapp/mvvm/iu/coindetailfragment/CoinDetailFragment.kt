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
import com.example.criptomonedasapp.mvvm.adapter.AsksAdapter
import com.example.criptomonedasapp.mvvm.adapter.BidsAdapter
import com.example.criptomonedasapp.utils.GetCoinCardModel.getCoinIcon
import com.example.criptomonedasapp.utils.GetCoinCardModel.getNameCoin

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

        setFragmentResultListener("requestKey") { key, bundle ->
            val resultCoinName = bundle.getString("coinNameKey")
            resultCoinName?.let {
                viewModel.getCoinDetail(resultCoinName)
                viewModel.getAsks(resultCoinName)
                viewModel.getBids(resultCoinName)
            }
        }

        viewModel.coinDetailModell.observe(viewLifecycleOwner) {
            with(binding){
                highCoinTxt.text = it.highValue
                lastCoinTxt.text = it.lastValue
                lowCoinTxt.text = it.lowValue
                coinNameTxt.text = getNameCoin(it.coinName)
                coinIconImg.setImageResource(getCoinIcon(it.coinName))
            }
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