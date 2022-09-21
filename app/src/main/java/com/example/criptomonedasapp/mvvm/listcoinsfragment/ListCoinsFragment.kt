package com.example.criptomonedas.mvvm.listcoinsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criptomonedasapp.R

import com.example.criptomonedasapp.databinding.FragmentListCoinsBinding
import com.example.criptomonedasapp.mvvm.interfaces.DetailCoinResultCallback
import com.example.criptomonedasapp.mvvm.listcoinsfragment.ListCoinsViewModel

class ListCoinsFragment() : Fragment(), DetailCoinResultCallback {

    private var _binding: FragmentListCoinsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListCoinsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentListCoinsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListCoinsViewModel::class.java)

        viewModel.getAll(this)

        viewModel.adapter.observe(viewLifecycleOwner) {
            binding.rvListCoins.adapter = it
            binding.rvListCoins.layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onItemConvention(nameC: String) {
        findNavController().navigate(R.id.action_listCoinsFragment_to_coinDetailFragment2)
    }


}