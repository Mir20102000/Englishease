package com.example.englishease.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "theory")
data class Theory (
    @PrimaryKey
    val theory_name: String,
    val theory_text: String
        )