package com.example.jetpackstudy.repository.room.converter

import android.R.attr
import androidx.room.TypeConverter
import com.example.jetpackstudy.repository.data.GitRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class RepoTypeConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        fun stringToRepo(data: String?): List<GitRepo> {
            return if (data == null) {
                Collections.emptyList()
            } else {
                val listType = object : TypeToken<List<GitRepo>>() {}.type

                return gson.fromJson(data, listType)
            }
        }

        @TypeConverter
        fun repoToString(list: List<GitRepo>): String {
            return gson.toJson(list)
        }
    }
}