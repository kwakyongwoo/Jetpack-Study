package com.example.jetpackstudy.main.list.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.R
import com.example.jetpackstudy.databinding.FragmentListBinding
import com.example.jetpackstudy.main.MainActivity
import com.example.jetpackstudy.main.list.adapter.OwnerAdapter
import com.example.jetpackstudy.main.list.viewmodel.ListViewModel
import com.example.jetpackstudy.main.search.adapter.RepoAdapter
import com.example.jetpackstudy.repository.room.converter.RepoTypeConverter

class ListFragment : Fragment() {

    private lateinit var vm: ListViewModel
    private lateinit var binding: FragmentListBinding
    private lateinit var ownerAdapter: OwnerAdapter
    private lateinit var menuDeleteAll: MenuItem

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_delete_all, menu)
        menuDeleteAll = menu.findItem(R.id.menu_delete_all)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        vm = ViewModelProviders.of(this).get(ListViewModel::class.java)

        ownerAdapter = OwnerAdapter({ owner -> onClickOwner(owner) }, { owner -> onLongClickOwner(owner) })

        binding.apply {
            listRcvOwner.apply {
                adapter = ownerAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }
        }

        vm.getAllOwner().observe(viewLifecycleOwner, Observer {
            ownerAdapter.getAllOwner(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                binding.listRcvOwner.adapter = ownerAdapter
                menuDeleteAll.isVisible = true
                (activity as MainActivity).supportActionBar?.apply {
                    title = "List"
                    setDisplayHomeAsUpEnabled(false)
                }
            }
            R.id.menu_delete_all -> {
                AlertDialog.Builder(context).run {
                    setTitle("전체 삭제")
                    setMessage("삭제하시겠습니까?")
                    setPositiveButton("예") { dialog, which ->
                        vm.deleteAll()
                    }
                    setNegativeButton("아니오") { dialog, which ->

                    }
                    show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        menuDeleteAll.isVisible = true
    }

    private fun onClickOwner(owner: String) {
        val repoAdapter = RepoAdapter()
        vm.getRepoInfo(owner).let {
            setHasOptionsMenu(true)
            (activity as MainActivity).supportActionBar?.apply {
                menuDeleteAll.isVisible = false
                title = it.owner
                setDisplayHomeAsUpEnabled(true)
            }
            repoAdapter.getAll(RepoTypeConverter.stringToRepo(it.repoList))
        }

        binding.listRcvOwner.adapter = repoAdapter
    }

    private fun onLongClickOwner(owner: String) {
        AlertDialog.Builder(context).run {
            setTitle("$owner")
            setMessage("삭제하시겠습니까?")
            setPositiveButton("예") { dialog, which ->
                vm.delete(owner)
            }
            setNegativeButton("아니오") { dialog, which ->

            }
            show()
        }
    }
}