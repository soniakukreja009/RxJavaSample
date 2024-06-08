package com.example.rxjavasample.domain.usecase

import com.example.rxjavasample.domain.Repository
import com.example.rxjavasample.data.entity.Comments
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

open class GetCommentsUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(
    ) : Single<Comments> {
        return repository.getComments()
    }
}