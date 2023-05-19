package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository
import com.example.englishease.domain.models.Result

class ShowResultsUseCase(private val domainRepository: DomainRepository) {
    suspend fun showResults(testName: String): List<Result> {
        return domainRepository.showResults(testName)
    }
}