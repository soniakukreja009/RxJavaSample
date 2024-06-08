package com.example.rxjavasample.data.datasource

import com.example.rxjavasample.data.ApiService
import com.example.rxjavasample.data.entity.Comments
import com.example.rxjavasample.data.entity.Post
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteSource {
    override fun getPosts(): Single<List<Post>> {
        return apiService.getPosts()
    }
    override fun getComments(): Single<Comments> {
        return apiService.getComments()
    }
}