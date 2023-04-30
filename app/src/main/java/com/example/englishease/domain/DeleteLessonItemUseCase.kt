package com.example.englishease.domain

class DeleteLessonItemUseCase (private val lessonListRepository: LessonListRepository) {

    fun deleteLessonItem(lessonItem: LessonItem) {
        lessonListRepository.deleteLessonItem(lessonItem)
    }
}