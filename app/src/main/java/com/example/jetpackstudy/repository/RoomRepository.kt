package com.example.jetpackstudy.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.jetpackstudy.repository.data.OwnerRepo
import com.example.jetpackstudy.repository.room.database.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository(application: Application) {
    private val myDatabase = MyDatabase.getInstance(application)!!
    private val ownerDao = myDatabase.ownerDao()
    private val allOwner: LiveData<List<String>> = ownerDao.getAllOwner()

    fun getAllOwner(): LiveData<List<String>> {
        return allOwner
    }

    suspend fun insert(item: OwnerRepo) {
        withContext(Dispatchers.IO) {
            ownerDao.insertOwner(item)
        }
    }

    suspend fun delete(owner: String) {
        withContext(Dispatchers.IO) {
            ownerDao.deleteOwner(owner)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            ownerDao.deleteAll()
        }
    }

    suspend fun getRepo(owner: String) = ownerDao.getOwner(owner)
}