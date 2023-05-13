package com.example.englishease.domain.repository

import com.example.englishease.domain.LessonItem

interface LessonListRepository {


    fun addLessonItem(lessonItem: LessonItem)

    fun deleteLessonItem(lessonItem: LessonItem)

    fun editLessonItem(lessonItem: LessonItem)

    fun getLessonItem(lessonItemId: Int): LessonItem

    fun getLessonList(): List<LessonItem>

}