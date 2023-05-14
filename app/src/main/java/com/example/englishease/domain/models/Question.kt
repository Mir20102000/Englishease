package com.example.englishease.domain.models

data class Question (
    val questionNumber: Int,
    val text: String,
    val variants: List<String>
        )