package com.example.criptomonedasapp.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.databinding.AsksRowItemBinding
import com.example.criptomonedasapp.domain.model.AsksModelData
import com.example.criptomonedasapp.utils.formatAsCurrency

class AsksAdapter() : ListAdapter<AsksModelData, AsksAdapter.ViewHolder>(difCallback){

    companion object{
        val difCallback = object : DiffUtil.ItemCallback<AsksModelData>(){
            override fun areItemsTheSame(oldItem: AsksModelData, newItem: AsksModelData): Boolean {
                return oldItem.price == newItem.price
            }
            override fun areContentsTheSame(oldItem: AsksModelData, newItem: AsksModelData): Boolean {
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

        fun bind(coin: AsksModelData){
            view.priceAsksTxt.text = coin.price.toDouble().formatAsCurrency()
            view.amountAsksTxt.text = coin.amount
        }
    }
}
