package com.example.englishease.domain.usecases

import com.example.englishease.domain.repository.DomainRepository
//import ru.studyguk.testingsystem.domain.repository.TestRepository

class DeleteTestUseCase(private val testRepository: DomainRepository) {
    suspend fun deleteTest(testName: String) {
        testRepository.deleteTest(testName)
    }
}