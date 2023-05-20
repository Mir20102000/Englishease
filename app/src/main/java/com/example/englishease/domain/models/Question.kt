package com.example.englishease.domain.models

data class Question (
    var questionNumber: Int,
    var questionText: String,
    var variants: List<String>,
    var points: List<Int>
        )