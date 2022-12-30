package com.octopus.socialnetwork.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.socialnetwork.data.local.entity.PostEntity

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM posts_table ORDER BY id DESC ")
    fun getAllPosts(): PagingSource<Int, PostEntity>

    @Query("SELECT * FROM posts_table WHERE  id = :postId ")
    fun getPostDetails(postId: Int): PostEntity

    @Query("DELETE FROM posts_table")
    suspend fun deleteAllPosts()

    @Query("UPDATE posts_table SET isLikedByUser = :isLikedByUser, totalLikes = :newLikesCount WHERE id = :id")
    suspend fun updatePostLikeStatus(id: Int, isLikedByUser: Boolean, newLikesCount: Int)
}