package com.example.criptomonedasapp.mvvm.detailscoinfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.criptomonedasapp.databinding.FragmentCoinDetailBinding
import com.example.criptomonedasapp.mvvm.listcoinsfragment.ListCoinsViewModel
import com.example.criptomonedasapp.utils.GetModelCardCoin.getIconCoin
import com.example.criptomonedasapp.utils.GetModelCardCoin.getNameCoin

class CoinDetailFragment : Fragment(){

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelObtNameCoin: ListCoinsViewModel
    private lateinit var viewModel: CoinDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CoinDetailViewModel::class.java)
        viewModelObtNameCoin = ViewModelProvider(this).get(ListCoinsViewModel::class.java)

        viewModel.getDetailCoin("btc_mxn")

        viewModel.detail.observe(viewLifecycleOwner) {
            with(binding){
                txtHigh.text = it.high
                txtLast.text = it.last
                txtLow.text = it.low
                txtNameCoin.text = getNameCoin(it.book)
                imgIconCoin.setImageResource(getIconCoin(it.book))
            }

        }

    }


}