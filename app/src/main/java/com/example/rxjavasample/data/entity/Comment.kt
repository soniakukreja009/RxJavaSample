package com.example.rxjavasample.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)
