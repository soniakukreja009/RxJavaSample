package com.example.rxjavasample.usecase

import com.example.rxjavasample.data.Repository
import com.example.rxjavasample.domain.Comments
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