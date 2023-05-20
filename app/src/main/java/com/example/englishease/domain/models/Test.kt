package com.example.englishease.domain.models

data class Test (
    val testNameTest: String,
    val theoryText: String,
    val questionCount: Int,
    val resultPoints: Int,
    val resultText: String,
    val questions: List<Question>
        )