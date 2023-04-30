package com.example.englishease.domain.usecase

import com.example.englishease.domain.LessonItem
import com.example.englishease.domain.LessonListRepository

class DeleteLessonItemUseCase (private val lessonListRepository: LessonListRepository) {

    fun deleteLessonItem(lessonItem: LessonItem) {
        lessonListRepository.deleteLessonItem(lessonItem)
    }
}