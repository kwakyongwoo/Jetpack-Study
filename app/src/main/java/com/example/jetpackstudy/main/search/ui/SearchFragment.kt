package com.example.jetpackstudy.main.search.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.R
import com.example.jetpackstudy.databinding.FragmentSearchBinding
import com.example.jetpackstudy.main.search.viewmodel.SearchViewModel
import com.example.jetpackstudy.main.search.adapter.RepoAdapter
import com.example.jetpackstudy.repository.data.GitRepo
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var vm: SearchViewModel
    private var list: List<GitRepo> = listOf()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        vm = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        val adapter = RepoAdapter()

        binding.apply {
            viewmodel = vm
            search_rcv_info.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }

            search_btn_search.setOnClickListener {
                val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view!!.windowToken, 0)
                vm.getAllReposApi().observe(this@SearchFragment, Observer {
                    list = it
                    adapter.getAll(it)
                })
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                list.let {
                    if (it.isEmpty()) Toast.makeText(context, "Nothing to Save", Toast.LENGTH_SHORT).show()
                    else {
                        vm.insert(it)
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}