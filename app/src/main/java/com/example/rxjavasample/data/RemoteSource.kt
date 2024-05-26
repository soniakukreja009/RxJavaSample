package com.example.rxjavasample.data

import com.example.rxjavasample.domain.Comments
import com.example.rxjavasample.domain.Post
import io.reactivex.rxjava3.core.Single

interface RemoteSource {
    fun getPosts() : Single<List<Post>>

    fun getComments(): Single<Comments>
}