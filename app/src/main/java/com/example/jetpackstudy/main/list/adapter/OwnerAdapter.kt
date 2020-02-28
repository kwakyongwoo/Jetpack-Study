package com.example.jetpackstudy.main.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.R
import com.example.jetpackstudy.main.list.viewholder.OwnerViewHolder

class OwnerAdapter(private val onClick: (String) -> Unit, private val onLongClick: (String) -> Unit) : RecyclerView.Adapter<OwnerViewHolder>() {
    private var list = listOf<String>()

    fun getAllOwner(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerViewHolder {
        val viewHolder = OwnerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_owner, parent, false))

        viewHolder.itemView.setOnClickListener {
            onClick(list[viewHolder.adapterPosition])
        }

        viewHolder.itemView.setOnLongClickListener {
            onLongClick(list[viewHolder.adapterPosition])
            true
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}