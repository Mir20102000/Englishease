package com.example.englishease.domain.usecases

import androidx.lifecycle.LiveData
import com.example.englishease.domain.repository.DomainRepository

class ShowSelectedTheoryUseCase(private val domainRepository: DomainRepository) {
    fun showSelectedTheory(theoryName: String): LiveData<String> {
        return domainRepository.showSelectedTheory(theoryName)
    }
}