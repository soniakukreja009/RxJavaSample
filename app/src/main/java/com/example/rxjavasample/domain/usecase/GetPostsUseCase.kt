package com.example.rxjavasample.domain.usecase

import com.example.rxjavasample.domain.Repository
import com.example.rxjavasample.data.entity.Post
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