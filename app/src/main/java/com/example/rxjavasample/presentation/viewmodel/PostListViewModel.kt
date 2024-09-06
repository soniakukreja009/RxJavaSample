package com.example.rxjavasample.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rxjavasample.ApiConstants
import com.example.rxjavasample.data.datasource.AppDatabase
import com.example.rxjavasample.data.entity.Post
import com.example.rxjavasample.presentation.state.StatePostList
import com.example.rxjavasample.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsUseCase,
    private val appDatabase: AppDatabase
): ViewModel() {

    val statePostsList = MutableLiveData<StatePostList>()

    init {
        getPostsList()
    }

    @SuppressLint("CheckResult")
    private fun getPostsList(): LiveData<StatePostList> {
        useCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { posts ->
                    appDatabase.postDao().deleteAll()
                    appDatabase.postDao().insertPostList(posts)
                    statePostsList.postValue(StatePostList.Success(posts))
                },
                { error ->
                    if (error.message == ApiConstants.CHECK_INTERNET_CONNECTION) {
                        val posts = appDatabase.postDao().getAllPosts()
                        statePostsList.postValue(StatePostList.Success(posts))
                    } else {
                        statePostsList.postValue(StatePostList.Failure(error.message ?: ""))
                    }
                }
            )
        return statePostsList
    }

    fun updateDBPostData(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.postDao().updatePost(post)
        }
    }
}