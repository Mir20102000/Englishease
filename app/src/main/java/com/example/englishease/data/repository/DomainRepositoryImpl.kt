package com.example.englishease.data.repository

import androidx.lifecycle.LiveData
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.models.User
import com.example.englishease.domain.repository.DomainRepository

object DomainRepositoryImpl: DomainRepository {
    override fun authorization(user: User) {
        TODO("Not yet implemented")
    }

    override fun registration(user: User) {
        TODO("Not yet implemented")
    }

    override fun showTheoryList(): LiveData<List<String>> {
        TODO("Not yet implemented")
    }

    override fun showTestsList(): LiveData<List<String>> {
        TODO("Not yet implemented")
    }

    override fun showTestResultsList(): LiveData<List<String>> {
        TODO("Not yet implemented")
    }

    override fun beginTest(testName: String): LiveData<String> {
        TODO("Not yet implemented")
    }

    override fun continueTest(testName: String, queNum: Int): LiveData<Question> {
        TODO("Not yet implemented")
    }

    override fun finishTest(testName: String, point: Int, userName: String): LiveData<String> {
        TODO("Not yet implemented")
    }

    override fun showSelectedTestResults(testName: String): LiveData<String> {
        TODO("Not yet implemented")
    }

    override fun showSelectedTheory(theoryName: String): LiveData<String> {
        TODO("Not yet implemented")
    }

}