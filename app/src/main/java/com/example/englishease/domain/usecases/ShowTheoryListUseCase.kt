package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository

class ShowTheoryListUseCase(private val domainRepository: DomainRepository) {
    suspend fun showTheoryList(): List<String> {
        return domainRepository.showTheoryList()
    }
}