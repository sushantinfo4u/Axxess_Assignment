package com.example.axxessassignment.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_data")
data class CommentEntity(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "comment") var comment: String,
        @ColumnInfo(name = "time") var time: String
)
