package com.example.rxjavasample.presentation.state

import com.example.rxjavasample.domain.Post

sealed class StatePostList {

    object Loading : StatePostList()
    data class Success(val postList: List<Post>) : StatePostList()
    data class Failure(val message: String) : StatePostList()
}