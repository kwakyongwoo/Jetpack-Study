package com.example.jetpackstudy.main.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.databinding.ItemOwnerBinding

class OwnerViewHolder(private val binding: ItemOwnerBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(owner: String) {
        binding.owner = owner
    }
}