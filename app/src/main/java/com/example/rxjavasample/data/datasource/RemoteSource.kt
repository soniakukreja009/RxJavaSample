package com.example.rxjavasample.data.datasource

import com.example.rxjavasample.data.entity.Comments
import com.example.rxjavasample.data.entity.Post
import io.reactivex.rxjava3.core.Single

interface RemoteSource {
    fun getPosts() : Single<List<Post>>

    fun getComments(): Single<Comments>
}