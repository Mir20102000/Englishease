package com.example.englishease.domain.usecases

import com.example.englishease.domain.repository.DomainRepository

class DeleteLessonUseCase(private val domainRepository: DomainRepository) {
    suspend fun deleteLesson(lessonName: String) {
        domainRepository.deleteLesson(lessonName)
    }
}