package com.example.englishease.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "result",
    foreignKeys = [
        ForeignKey(
            entity = Test::class,
            parentColumns = ["test_name"],
            childColumns = ["test_name"],
            onDelete = CASCADE
        )]
)
data class Result(
    @PrimaryKey(autoGenerate = true)
    val result_id: Int,
    val test_name: String,
    val result_name: String,
    val user: String,
    val points: Int,
    val date: String
)
