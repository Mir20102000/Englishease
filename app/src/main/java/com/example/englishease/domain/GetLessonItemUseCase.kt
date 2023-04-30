package com.example.englishease.domain

class GetLessonItemUseCase (private val lessonListRepository: LessonListRepository) {

    fun getLessonItem(lessonItemId: Int): LessonItem {
        return lessonListRepository.getLessonItem(lessonItemId)
    }
}