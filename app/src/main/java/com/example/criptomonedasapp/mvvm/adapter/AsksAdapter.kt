package com.example.criptomonedasapp.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.criptomonedasapp.R
import com.example.criptomonedasapp.model.network.AsksModel


class AsksAdapter(private val dataSet: List<AsksModel>) :
    RecyclerView.Adapter<AsksAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val amount: TextView

        init {
            price = view.findViewById(R.id.txtPrice)
            amount = view.findViewById(R.id.txtAmount)
        }

        fun connectItem(ask: AsksModel){
            price.text = ask.price
            amount.text = ask.amount
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.asks_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.connectItem(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
