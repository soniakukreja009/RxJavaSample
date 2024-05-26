package com.example.rxjavasample.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavasample.domain.Post
import com.example.rxjavasample.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsUseCase
): ViewModel() {

    val postsList = MutableLiveData<List<Post>>()
    val showError = MutableLiveData<Boolean>()

    init {
        getPostsList()
    }

    @SuppressLint("CheckResult")
    private fun getPostsList(): LiveData<List<Post>> {
        useCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { posts -> postsList.postValue(posts) },
                { error -> showError() }
            )
        return postsList
    }

    private fun showError() {
        showError.postValue(true)
    }

    fun onItemClicked(itemId: Int) {
        postsList.value?.single { it.id == itemId }?.apply { isFav = !isFav }
    }
}