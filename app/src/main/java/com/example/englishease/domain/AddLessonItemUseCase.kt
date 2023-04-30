package com.example.englishease.domain

class AddLessonItemUseCase(private val lessonListRepository: LessonListRepository) {

    fun addLessonItem(lessonItem: LessonItem) {
        lessonListRepository.addLessonItem(lessonItem)
    }
}