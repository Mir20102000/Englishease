package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository

class BeginTestUseCase(private val domainRepository: DomainRepository) {
    suspend fun beginTest(testName: String): String {
        return domainRepository.beginTest(testName)
    }
}