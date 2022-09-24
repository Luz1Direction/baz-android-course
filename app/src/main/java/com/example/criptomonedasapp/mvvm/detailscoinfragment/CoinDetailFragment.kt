package com.example.criptomonedasapp.mvvm.detailscoinfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.criptomonedasapp.databinding.CoinDetailFragmentBinding
import com.example.criptomonedasapp.mvvm.listcoinsfragment.CoinListViewModel
import com.example.criptomonedasapp.utils.GetCoinCardModel.getCoinIcon
import com.example.criptomonedasapp.utils.GetCoinCardModel.getNameCoin

class CoinDetailFragment : Fragment(){

    private var _binding: CoinDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelObtNameCoin: CoinListViewModel
    private lateinit var viewModel: CoinDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CoinDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinDetailViewModel::class.java)
        viewModelObtNameCoin = ViewModelProvider(this).get(CoinListViewModel::class.java)

        viewModel.getDetailCoin("btc_mxn")

        viewModel.detail.observe(viewLifecycleOwner) {
            with(binding){
                txtHigh.text = it.highValue
                txtLast.text = it.lastValue
                txtLow.text = it.lowValue
                txtNameCoin.text = getNameCoin(it.coinName)
                imgIconCoin.setImageResource(getCoinIcon(it.coinName))
            }

        }

    }


}