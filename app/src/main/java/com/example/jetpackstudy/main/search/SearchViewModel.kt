package com.example.jetpackstudy.main.search

import android.app.Application
import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.main.search.adapter.SearchAdapter
import com.example.jetpackstudy.repository.ApiRepository
import com.example.jetpackstudy.repository.data.GitRepo
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class SearchViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ApiRepository = ApiRepository(application)

    var owner: ObservableField<String> = ObservableField("")
    var reposName: ObservableField<String> = ObservableField("")

    fun getAllReposApi(): LiveData<List<GitRepo>> = liveData(Dispatchers.IO) {
        try {
            val user = repository.getAllReposApi(owner.get()!!)
            emit(user)
        } catch (e: HttpException) {
            emit(listOf())
        }
    }
}