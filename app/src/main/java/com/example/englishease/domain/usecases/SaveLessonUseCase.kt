package com.example.englishease.domain.usecases

import com.example.englishease.domain.repository.DomainRepository

class SaveLessonUseCase(private val domainRepository: DomainRepository) {

    suspend fun saveLesson(
        lessonName: String,
        lessonDesc: String,
        lessonContent: String
    ) {
        domainRepository.saveLesson(lessonName, lessonDesc, lessonContent)
    }
}