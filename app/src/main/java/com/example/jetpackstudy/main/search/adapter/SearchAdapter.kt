package com.example.jetpackstudy.main.search.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.R
import com.example.jetpackstudy.main.search.viewholder.SearchViewHolder
import com.example.jetpackstudy.repository.data.GitRepo

class SearchAdapter: RecyclerView.Adapter<SearchViewHolder>() {
    private var list = listOf<GitRepo>()

    fun getAll(list: List<GitRepo>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val viewHolder = SearchViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false))

        viewHolder.itemView.setOnClickListener {
            parent.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list[viewHolder.adapterPosition].url)))
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}