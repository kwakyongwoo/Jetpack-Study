package com.example.jetpackstudy.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackstudy.repository.data.OwnerRepo

interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOwner(item: OwnerRepo)

    @Delete
    suspend fun deleteOwner(item: OwnerRepo)

    @Query("DELETE FROM owner_repo")
    suspend fun deleteAll()

    @Query("SELECT owner FROM owner_repo")
    fun getAllOwner(): LiveData<List<String>>

    @Query("SELECT * FROM owner_repo WHERE owner=:owner")
    suspend fun getOwner(owner: String): OwnerRepo
}