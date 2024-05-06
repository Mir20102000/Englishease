package com.example.englishease.domain.usecases

import com.example.englishease.domain.repository.DomainRepository

class ShowLessonDescUseCase(private val domainRepository: DomainRepository) {
    suspend fun showLessonDesc() : List<String> {
        return domainRepository.showLessonDesc()
    }
}