package com.example.jetpackstudy.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.jetpackstudy.repository.data.OwnerRepo
import com.example.jetpackstudy.repository.room.database.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository(application: Application) {
    private val ownerDatabase = MyDatabase.getInstance(application)!!
    private val ownerDao = ownerDatabase.ownerDao()
    private val allOwner:LiveData<List<String>> = ownerDao.getAllOwner()

    fun getAll(): LiveData<List<String>> {
        return allOwner
    }

    suspend fun insert(item: OwnerRepo) {
        withContext(Dispatchers.IO) {
            ownerDao.insertOwner(item)
        }
    }

    suspend fun delete(item: OwnerRepo) {
        withContext(Dispatchers.IO) {
            ownerDao.deleteOwner(item)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            ownerDao.deleteAll()
        }
    }

    suspend fun getOwner(owner: String) = ownerDao.getOwner(owner)
}