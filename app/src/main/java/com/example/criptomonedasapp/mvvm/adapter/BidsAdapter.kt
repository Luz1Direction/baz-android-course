package com.example.criptomonedasapp.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.databinding.BidsRowItemBinding
import com.example.criptomonedasapp.model.network.BidsModel
import com.example.criptomonedasapp.utils.formatAsCurrency

class BidsAdapter() : ListAdapter<BidsModel, BidsAdapter.ViewHolder>(difCallbackBids) {

    companion object {
        val difCallbackBids = object : DiffUtil.ItemCallback<BidsModel>() {
            override fun areItemsTheSame(oldItem: BidsModel, newItem: BidsModel): Boolean {
                return oldItem.coinName == newItem.coinName
            }

            override fun areContentsTheSame(oldItem: BidsModel, newItem: BidsModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = BidsRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class ViewHolder(private val view: BidsRowItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(coin: BidsModel) {
            view.priceBidsTxt.text = coin.price.toDouble().formatAsCurrency()
            view.amountBidsTxt.text = coin.amount
        }
    }
}