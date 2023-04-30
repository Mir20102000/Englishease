package com.example.englishease.domain.usecase

import com.example.englishease.domain.LessonItem
import com.example.englishease.domain.LessonListRepository

class GetLessonListUseCase (private val lessonListRepository: LessonListRepository) {

    fun getLessonList(): List<LessonItem> {
        return lessonListRepository.getLessonList()
    }
}