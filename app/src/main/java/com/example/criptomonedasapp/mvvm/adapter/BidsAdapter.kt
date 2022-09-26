package com.example.criptomonedasapp.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.R
import com.example.criptomonedasapp.model.network.AsksModel
import com.example.criptomonedasapp.model.network.BidsModel


class BidsAdapter(private val dataSet: List<BidsModel>) :
    RecyclerView.Adapter<BidsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val price: TextView
        private val amount: TextView

        init {
            price = view.findViewById(R.id.txtPrice)
            amount = view.findViewById(R.id.txtAmount)
        }

        fun connectItem(bids: BidsModel){
            price.text = bids.price
            amount.text = bids.amount
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.bids_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.connectItem(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
