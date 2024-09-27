package com.example.criptomonedasapp.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.databinding.BidsRowItemBinding
import com.example.criptomonedasapp.domain.model.BidsModelData
import com.example.criptomonedasapp.utils.formatAsCurrency

class BidsAdapter() : ListAdapter<BidsModelData, BidsAdapter.ViewHolder>(difCallbackBids) {

    companion object {
        val difCallbackBids = object : DiffUtil.ItemCallback<BidsModelData>() {
            override fun areItemsTheSame(oldItem: BidsModelData, newItem: BidsModelData): Boolean {
                return oldItem.coinName == newItem.coinName
            }

            override fun areContentsTheSame(oldItem: BidsModelData, newItem: BidsModelData): Boolean {
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

        fun bind(coin: BidsModelData) {
            view.priceBidsTxt.text = coin.price.toDouble().formatAsCurrency()
            view.amountBidsTxt.text = coin.amount
        }
    }
}