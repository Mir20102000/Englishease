package com.example.englishease.domain.usecases

import com.example.englishease.domain.models.Question
import com.example.englishease.domain.repository.DomainRepository

class ShowQuestionsListUseCase(private val domainRepository: DomainRepository) {
    suspend fun showQuestionsList(testName: String): List<Question> {
        return domainRepository.showQuestionsList(testName)
    }
}