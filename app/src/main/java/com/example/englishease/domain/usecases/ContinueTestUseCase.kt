package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.repository.DomainRepository

class ContinueTestUseCase(private val domainRepository: DomainRepository) {
    suspend fun continueTest(testName: String, queNum: Int): Question {
        return domainRepository.continueTest(testName, queNum)
    }
}