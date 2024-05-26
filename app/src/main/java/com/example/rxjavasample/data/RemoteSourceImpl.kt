package com.example.rxjavasample.data

import com.example.rxjavasample.domain.ApiService
import com.example.rxjavasample.domain.Comments
import com.example.rxjavasample.domain.Post
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