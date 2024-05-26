package com.example.rxjavasample.domain

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET(ApiConstants.GET_POSTS)
    fun getPosts(): Single<List<Post>>

    @GET(ApiConstants.GET_COMMENTS)
    fun getComments(): Single<Comments>
}