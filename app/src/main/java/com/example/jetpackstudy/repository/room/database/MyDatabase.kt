package com.example.jetpackstudy.repository.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpackstudy.repository.data.GitRepo
import com.example.jetpackstudy.repository.data.OwnerRepo
import com.example.jetpackstudy.repository.room.dao.OwnerDao

@Database(entities = [GitRepo::class, OwnerRepo::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun ownerDao(): OwnerDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, MyDatabase::class.java, "MyDatabase.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }
}