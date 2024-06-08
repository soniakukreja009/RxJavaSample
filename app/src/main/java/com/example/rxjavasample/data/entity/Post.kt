package com.example.rxjavasample.data.entity

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var isFav: Boolean = false,
)
