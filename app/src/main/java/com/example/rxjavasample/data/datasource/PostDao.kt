package com.example.rxjavasample.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.rxjavasample.data.entity.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM postTable")
    fun getAllPosts(): List<Post>

    @Insert
    fun insertPostList(list: List<Post>)

    @Query("DELETE FROM postTable")
    fun deleteAll()

    @Update
    fun updatePost(post: Post): Int
}