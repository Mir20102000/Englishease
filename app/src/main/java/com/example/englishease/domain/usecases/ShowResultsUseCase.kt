package com.example.englishease.domain.usecases

import com.example.englishease.domain.repository.DomainRepository
import com.example.englishease.domain.models.Conclusion

class ShowResultsUseCase(private val domainRepository: DomainRepository) {
    suspend fun showResults(testName: String): List<Conclusion> {
        return domainRepository.showResults(testName)
    }
}