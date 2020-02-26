package com.example.jetpackstudy.main.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.R
import com.example.jetpackstudy.databinding.FragmentSearchBinding
import com.example.jetpackstudy.main.search.adapter.SearchAdapter
import com.example.jetpackstudy.repository.data.GitRepo
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var vm: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        vm = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        val adapter = SearchAdapter()

        binding.apply {
            viewmodel = vm
            search_rcv_info.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }

            search_btn_search.setOnClickListener {
                vm.getAllReposApi().observe(this@SearchFragment, Observer {
                    adapter.getAll(it)
                })
            }
        }
    }
}