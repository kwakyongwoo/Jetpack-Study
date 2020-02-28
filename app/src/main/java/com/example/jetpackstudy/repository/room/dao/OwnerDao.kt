package com.example.jetpackstudy.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.jetpackstudy.repository.data.OwnerRepo

@Dao
interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOwner(item: OwnerRepo)

    @Query("DELETE FROM owner_repo WHERE owner=:owner")
    fun deleteOwner(owner: String)

    @Query("DELETE FROM owner_repo")
    fun deleteAll()

    @Query("SELECT owner FROM owner_repo")
    fun getAllOwner(): LiveData<List<String>>

    @Query("SELECT * FROM owner_repo WHERE owner=:owner")
    fun getOwner(owner: String): OwnerRepo
}