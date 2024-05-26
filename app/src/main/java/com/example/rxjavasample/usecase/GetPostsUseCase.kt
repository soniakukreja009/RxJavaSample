package com.example.rxjavasample.usecase

import com.example.rxjavasample.data.Repository
import com.example.rxjavasample.domain.Post
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(
    ) : Single<List<Post>> {
        return repository.getPosts()
    }
}