package com.example.rxjavasample.data

import com.example.rxjavasample.ApiConstants
import com.example.rxjavasample.data.entity.Comments
import com.example.rxjavasample.data.entity.Post
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET(ApiConstants.GET_POSTS)
    fun getPosts(): Single<List<Post>>

    @GET(ApiConstants.GET_COMMENTS)
    fun getComments(): Single<Comments>
}