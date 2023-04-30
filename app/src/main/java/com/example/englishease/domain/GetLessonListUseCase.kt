package com.example.englishease.domain

class GetLessonListUseCase (private val lessonListRepository: LessonListRepository) {

    fun getLessonList(): List<LessonItem> {
        return lessonListRepository.getLessonList()
    }
}