package com.example.jetpackstudy.main.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.databinding.ItemRepoBinding
import com.example.jetpackstudy.repository.data.GitRepo

class RepoViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: GitRepo) {
        binding.item = item
    }
}