package com.example.englishease.domain.repository

import com.example.englishease.data.models.Conclusion
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.models.User
import com.example.englishease.domain.models.Result

interface DomainRepository {

    fun authorization(user: User): Boolean
    fun registration(user: User): Boolean


    suspend fun beginTest(testName: String): String
    suspend fun continueTest(testName: String, questionNumber: Int): Question
    suspend fun finishTest(testName: String, points: Int, userName: String): String


    suspend fun showTheoryList(): List<String>
    suspend fun showLessonsNameList(): List<String>
    suspend fun showQuestionsList(testName: String): List<Question>
    suspend fun showLessonDesc(): List<String>
    suspend fun showResults(testName: String): List<Result>
    suspend fun getCountQuestion(testName: String): Int

    suspend fun saveTest(
        testName: String,
        declTest: String,
        queCount: Int,
        questionList: List<Question>,
        textResultList: List<com.example.englishease.domain.models.Conclusion>
    )

    suspend fun saveLesson(
        lessonName: String,
        lessonDesc: String,
        lessonContent: String
    )

    suspend fun deleteTest(testName: String)
    suspend fun deleteLesson(lessonName: String)
}