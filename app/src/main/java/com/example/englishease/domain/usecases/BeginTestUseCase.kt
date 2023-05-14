package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository

class BeginTestUseCase(private val domainRepository: DomainRepository) {
    fun beginTest(testName: String): LiveData<String> {
        return domainRepository.beginTest(testName)
    }
}