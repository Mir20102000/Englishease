package com.example.englishease.domain.usecases

import com.example.englishease.domain.repository.DomainRepository

class ShowLessonsListUseCase(private val domainRepository: DomainRepository) {
    suspend fun showLessonsList(): List<String> {
        return domainRepository.showLessonsList()
    }
}