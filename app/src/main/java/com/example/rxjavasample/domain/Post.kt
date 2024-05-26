package com.example.rxjavasample.domain

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var isFav: Boolean = false,
)
