package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository

class ShowTestResultsListUseCase(private val domainRepository: DomainRepository) {
    fun showTestResultsList(): LiveData<List<String>> {
        return domainRepository.showTestResultsList()
    }
}