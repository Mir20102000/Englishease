package com.example.englishease.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "question",
    foreignKeys = [
        ForeignKey(
            entity = Test::class,
            parentColumns = ["name"],
            childColumns = ["test_name"],
            onDelete = CASCADE
        )]
)
data class Question(
    @PrimaryKey(autoGenerate = true)
    val question_id: Int,
    val test_name: String,
    val question_number: Int,
    val question_text: String,
    val answer_one: String,
    val answer_two: String,
    val answer_three: String,
    val answer_four: String,
    val point_one: Int,
    val point_two: Int,
    val point_three: Int,
    val point_four: Int
)
