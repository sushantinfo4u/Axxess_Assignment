package com.example.axxessassignment.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CommentDao {

    @Query("SELECT * FROM comment_data WHERE title LIKE :title")
    fun findByTitle(title: String): List<CommentEntity>

    @Insert
    fun insertAll(vararg commentData: CommentEntity)

}