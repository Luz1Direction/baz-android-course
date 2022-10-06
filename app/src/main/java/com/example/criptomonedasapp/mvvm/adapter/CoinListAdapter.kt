package com.example.criptomonedasapp.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.databinding.CoinListItemBinding
import com.example.criptomonedasapp.model.CoinCardModel
import com.example.criptomonedasapp.mvvm.interfaces.CoinDetailResultCallback


class CoinListAdapter(private val actionDetail: CoinDetailResultCallback) :
    ListAdapter<CoinCardModel, CoinListAdapter.ViewHolder>(difCallback){

    companion object{
        val difCallback = object : DiffUtil.ItemCallback<CoinCardModel>(){
            override fun areItemsTheSame(oldItem: CoinCardModel, newItem: CoinCardModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CoinCardModel, newItem: CoinCardModel): Boolean {
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

        fun bind(coin: CoinCardModel, actionDetail: CoinDetailResultCallback){
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
