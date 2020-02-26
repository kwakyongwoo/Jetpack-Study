package com.example.jetpackstudy.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.jetpackstudy.repository.api.ApiService
import com.example.jetpackstudy.repository.api.RetrofitClient
import com.example.jetpackstudy.repository.data.GitRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiRepository(application: Application) {
    private var client: ApiService = RetrofitClient.webService

    suspend fun getReposApi(owner: String, name: String) = client.getRepos(owner, name)

    suspend fun getAllReposApi(owner: String) = client.getAllRepos(owner)
}