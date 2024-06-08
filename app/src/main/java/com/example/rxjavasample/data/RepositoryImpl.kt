package com.example.rxjavasample.data

import com.example.rxjavasample.data.datasource.RemoteSource
import com.example.rxjavasample.data.entity.Comments
import com.example.rxjavasample.data.entity.Post
import com.example.rxjavasample.domain.Repository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource
) : Repository {
    override fun getPosts(): Single<List<Post>> {
        return remoteSource.getPosts()
    }

    override fun getComments(): Single<Comments> {
        return remoteSource.getComments()
    }
}