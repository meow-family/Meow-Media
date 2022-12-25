package com.octopus.socialnetwork.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.octopus.socialnetwork.data.local.entity.CommentEntity
import com.octopus.socialnetwork.data.local.entity.PostEntity

@Dao
interface CommentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentEntity>)

    @Query("SELECT * FROM comment_table WHERE postId = :postId")
    fun getAllComment(postId: Int): PagingSource<Int, CommentEntity>

    @Query("DELETE FROM comment_table")
    suspend fun deleteAllComment()

    @Query("UPDATE comment_table SET isLikedByUser = :isLikedByUser, totalLikes = :newLikesCount WHERE commentId = :id")
    suspend fun updateCommentLikeStatus(id: Int, isLikedByUser: Boolean, newLikesCount: Int)
}