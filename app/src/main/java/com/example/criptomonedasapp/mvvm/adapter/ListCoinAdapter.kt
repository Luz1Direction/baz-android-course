package com.example.criptomonedasapp.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.R
import com.example.criptomonedasapp.model.CoinsModelCard
import com.example.criptomonedasapp.mvvm.interfaces.CoinDetailResultCallback


class ListCoinAdapter(private val dataSet: List<CoinsModelCard>, val actionDetail: CoinDetailResultCallback) :
    RecyclerView.Adapter<ListCoinAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameCoin: TextView
        private val maxValue: TextView
        private val minValue: TextView
        private val iconCoin: ImageView
        private val cardView: View

        init {
            nameCoin = view.findViewById(R.id.txtNameCoin)
            maxValue= view.findViewById(R.id.txtMaxValue)
            minValue = view.findViewById(R.id.txtMinValue)
            iconCoin = view.findViewById(R.id.imgCoin)
            cardView = view.findViewById(R.id.clContainer)
        }

        fun enlazar(coin : CoinsModelCard, actionDetail: CoinDetailResultCallback){
            nameCoin.text = coin.coinName
            maxValue.text = coin.maxValue
            minValue.text = coin.minValue
            iconCoin.setImageResource(coin.drawable)

            cardView.setOnClickListener {
                actionDetail.goCoinDetail(coin.coinName)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.coin_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.enlazar(dataSet[position], actionDetail)
    }

    override fun getItemCount() = dataSet.size
}
