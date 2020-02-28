package com.example.jetpackstudy.main.search.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.jetpackstudy.repository.ApiRepository
import com.example.jetpackstudy.repository.RoomRepository
import com.example.jetpackstudy.repository.data.GitRepo
import com.example.jetpackstudy.repository.data.OwnerRepo
import com.example.jetpackstudy.repository.room.converter.RepoTypeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ApiRepository = ApiRepository(application)
    private val roomRepository: RoomRepository = RoomRepository(application)

    var owner: ObservableField<String> = ObservableField("")
    var reposName: ObservableField<String> = ObservableField("")

    private var searchOwner: String? = null

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getAllReposApi(): LiveData<List<GitRepo>> = liveData(Dispatchers.IO) {
        try {
            val user = repository.getAllReposApi(owner.get()!!)
            searchOwner = owner.get()
            emit(user)
        } catch (e: HttpException) {
            emit(listOf())
        }
    }

    fun insert(list: List<GitRepo>) = viewModelScope.launch {
        OwnerRepo(searchOwner!!, RepoTypeConverter.repoToString(list)).let {
            roomRepository.insert(it)
        }
    }
}