package com.example.englishease.domain

data class LessonItem(
    val id: Int,
    val name: String,
    val finished: Boolean,
    val toLesson: Boolean
)
