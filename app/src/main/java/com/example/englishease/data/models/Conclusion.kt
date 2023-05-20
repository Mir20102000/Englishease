package com.example.englishease.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "text_result",
    foreignKeys = [
        ForeignKey(
            entity = Test::class,
            parentColumns = ["name"],
            childColumns = ["test_name"],
            onDelete = CASCADE
        )]
)
data class Conclusion(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val test_name: String,
    val min_border: Int,
    val max_border: Int,
    val text: String
)