package com.example.englishease.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "test",
    foreignKeys = [
        ForeignKey(
            entity = Theory::class,
            parentColumns = ["theory_name"],
            childColumns = ["theory_name"],
            onDelete = ForeignKey.CASCADE
        )])
data class Test(
    @PrimaryKey
    val test_name: String,
    val theory_name: String,
)