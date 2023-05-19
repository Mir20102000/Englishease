package com.example.englishease.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class Test(
    @PrimaryKey
    val name: String,
    val theory_text: String,
    val question_count: Int
)