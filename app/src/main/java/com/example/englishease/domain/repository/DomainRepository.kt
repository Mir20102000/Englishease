package com.example.englishease.domain.repository

import androidx.lifecycle.LiveData
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.models.User

interface DomainRepository {

    fun authorization(user: User)

    fun registration(user: User)

    fun showTheoryList(): LiveData<List<String>>

    fun showTestsList(): LiveData<List<String>>

    fun showTestResultsList(): LiveData<List<String>>

    fun beginTest(testName: String): LiveData<String>

    fun continueTest(testName: String, queNum: Int): LiveData<Question>

    fun finishTest(testName: String, point: Int, userName: String): LiveData<String>

    fun showSelectedTestResults(testName: String): LiveData<String>

    fun showSelectedTheory(theoryName: String): LiveData<String>

}