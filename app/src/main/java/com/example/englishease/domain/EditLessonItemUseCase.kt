package com.example.englishease.domain

class EditLessonItemUseCase (private val lessonListRepository: LessonListRepository) {

    fun editLessonItem(lessonItem: LessonItem) {
        lessonListRepository.editLessonItem(lessonItem)
    }
}