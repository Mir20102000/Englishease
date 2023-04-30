package com.example.englishease.domain.usecase

import com.example.englishease.domain.LessonItem
import com.example.englishease.domain.LessonListRepository

class AddLessonItemUseCase(private val lessonListRepository: LessonListRepository) {

    fun addLessonItem(lessonItem: LessonItem) {
        lessonListRepository.addLessonItem(lessonItem)
    }
}