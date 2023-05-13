package com.example.englishease.domain.usecase

import com.example.englishease.domain.LessonItem
import com.example.englishease.domain.repository.LessonListRepository

class EditLessonItemUseCase (private val lessonListRepository: LessonListRepository) {

    fun editLessonItem(lessonItem: LessonItem) {
        lessonListRepository.editLessonItem(lessonItem)
    }
}