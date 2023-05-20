package com.example.englishease.domain.usecases

import com.example.englishease.domain.repository.DomainRepository

class GetCountQuestionUseCase(private val domainRepository: DomainRepository) {
    suspend fun getCountQue(testName: String): Int {
        return domainRepository.getCountQuestion(testName)
    }
}