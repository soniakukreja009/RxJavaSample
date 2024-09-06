package com.example.rxjavasample.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rxjavasample.data.entity.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}