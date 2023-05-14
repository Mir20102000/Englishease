package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.repository.DomainRepository

class ContinueTestUseCase(private val domainRepository: DomainRepository) {
    fun continueTest(testName: String, queNum: Int): LiveData<Question> {
        return domainRepository.continueTest(testName, queNum)
    }
}