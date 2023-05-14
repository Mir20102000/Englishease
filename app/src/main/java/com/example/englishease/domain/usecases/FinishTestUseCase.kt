package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository

class FinishTestUseCase(private val domainRepository: DomainRepository) {
    fun finishTest(testName: String, point: Int, userName: String): LiveData<String> {
        return domainRepository.finishTest(testName, point, userName)
    }
}