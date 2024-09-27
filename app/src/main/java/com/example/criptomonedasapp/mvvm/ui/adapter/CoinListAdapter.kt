package com.example.criptomonedasapp.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.databinding.CoinListItemBinding
import com.example.criptomonedasapp.data.network.model.CoinCardModel
import com.example.criptomonedasapp.domain.model.CoinCardData
import com.example.criptomonedasapp.mvvm.interfaces.CoinDetailResultCallback


class CoinListAdapter(private val actionDetail: CoinDetailResultCallback) :
    ListAdapter<CoinCardData, CoinListAdapter.ViewHolder>(difCallback){

    companion object{
        val difCallback = object : DiffUtil.ItemCallback<CoinCardData>(){
            override fun areItemsTheSame(oldItem: CoinCardData, newItem: CoinCardData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CoinCardData, newItem: CoinCardData): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = CoinListItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.bind(getItem(position), actionDetail)
    }

    class ViewHolder(private val view: CoinListItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(coin: CoinCardData, actionDetail: CoinDetailResultCallback){
            view.coinNameTxt.text = coin.coinName
            view.coinImage.setImageResource(coin.drawable)
            view.maxValueTxt.text = coin.maxValue
            view.minValueTxt .text = coin.minValue

            view.coinCardModel.setOnClickListener{
                actionDetail.goCoinDetail(coin.id)
            }
        }
    }
}
