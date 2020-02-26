package com.example.jetpackstudy.repository.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GitRepo(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String,
    @SerializedName("created_at") val createDate: String,
    @SerializedName("language") val language: String
)