package com.example.jetpackstudy.main.list.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.jetpackstudy.repository.RoomRepository
import com.example.jetpackstudy.repository.data.OwnerRepo
import kotlinx.coroutines.*

class ListViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RoomRepository(application)
    private val allOwner: LiveData<List<String>> = repository.getAllOwner()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getAllOwner(): LiveData<List<String>> {
        return allOwner
    }

    fun delete(owner: String) = viewModelScope.launch {
        repository.delete(owner)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getRepoInfo(owner: String): OwnerRepo = runBlocking(Dispatchers.IO) {
        repository.getRepo(owner)
    }
}