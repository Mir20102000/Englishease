package com.example.englishease.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson")
data class Lesson(
    @PrimaryKey
    val name: String,
    val description: String,
    val content: String
)