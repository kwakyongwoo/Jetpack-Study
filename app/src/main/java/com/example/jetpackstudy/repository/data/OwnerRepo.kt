package com.example.jetpackstudy.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.jetpackstudy.repository.room.converter.RepoTypeConverter
import java.util.*

@Entity(tableName = "owner_repo")
data class OwnerRepo(
    @PrimaryKey val owner: String,
    val repoList: String
)