package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository

class ShowSelectedTestResults(private val domainRepository: DomainRepository) {
    fun showSelectedTestResults(testName: String): LiveData<String> {
        return domainRepository.showSelectedTestResults(testName)
    }
}