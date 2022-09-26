package com.example.criptomonedasapp.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.databinding.AsksRowItemBinding
import com.example.criptomonedasapp.model.network.AsksModel


class AsksAdapter() : ListAdapter<AsksModel, AsksAdapter.ViewHolder>(difCallback){

    companion object{
        val difCallback = object : DiffUtil.ItemCallback<AsksModel>(){
            override fun areItemsTheSame(oldItem: AsksModel, newItem: AsksModel): Boolean {
                return oldItem.coinName == newItem.coinName
            }
            override fun areContentsTheSame(oldItem: AsksModel, newItem: AsksModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = AsksRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class ViewHolder(private val view: AsksRowItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(coin: AsksModel){
            view.priceAsksTxt.text = coin.price
            view.amountAsksTxt.text = coin.amount
        }
    }
}
