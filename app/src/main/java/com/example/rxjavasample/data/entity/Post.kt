package com.example.rxjavasample.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postTable")
data class Post(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    var isFav: Boolean = false,
)
