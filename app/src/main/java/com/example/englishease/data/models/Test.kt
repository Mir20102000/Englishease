package com.example.englishease.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "test",
    foreignKeys = [
        ForeignKey(
            entity = Lesson::class,
            parentColumns = ["name"],
            childColumns = ["name"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class Test(
    @PrimaryKey
    val name: String,
    val theory_text: String,
    val question_count: Int
)