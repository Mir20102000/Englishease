package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository

class ShowTestsListUseCase(private val domainRepository: DomainRepository) {
    fun showTestsList(): LiveData<List<String>> {
        return domainRepository.showTestsList()
    }
}