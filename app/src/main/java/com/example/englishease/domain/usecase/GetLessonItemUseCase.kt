package com.example.englishease.domain.usecase

import com.example.englishease.domain.LessonItem
import com.example.englishease.domain.repository.LessonListRepository

class GetLessonItemUseCase (private val lessonListRepository: LessonListRepository) {

    fun getLessonItem(lessonItemId: Int): LessonItem {
        return lessonListRepository.getLessonItem(lessonItemId)
    }
}