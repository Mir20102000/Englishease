package com.example.englishease.domain.repository

import androidx.lifecycle.LiveData
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.models.User
import com.example.englishease.domain.models.Result

interface DomainRepository {

    fun authorization(user: User): Boolean
    fun registration(user: User): Boolean


    suspend fun beginTest(testName: String): String
    suspend fun continueTest(testName: String, questionNumber: Int): Question
    suspend fun finishTest(testName: String, points: Int, userName: String): String


    suspend fun showTheoryList(): List<String>
    suspend fun showResults(testName: String): List<Result>
    suspend fun getCountQuestion(testName: String): Int




}