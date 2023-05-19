package com.example.englishease.data.repository

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.englishease.data.database.AppDatabase
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.models.Result
import com.example.englishease.domain.models.User
import com.example.englishease.domain.repository.DomainRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class DomainRepositoryImpl(private val application: Application): DomainRepository {
    private var db = AppDatabase.getInstance(application)
    override fun authorization(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun registration(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun beginTest(testName: String): String {
        return db.appDao().getTheoryText(testName)
    }

    override suspend fun continueTest(testName: String, questionNumber: Int): Question {
        val questionData = db.appDao().getQuestionInfo(testName, questionNumber)
        return mapQuestionToDomain(questionData)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun finishTest(testName: String, points: Int, userName: String): String {
        val date = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            .toString()
        db.appDao().insertResult(com.example.englishease.data.models.Result(
            0, testName, userName, points, date))
        return db.appDao().getResult(testName, points)
    }

    override suspend fun showResults(testName: String): List<Result> {
        val resultData = db.appDao().getResults(testName)
        val list = ArrayList<com.example.englishease.domain.models.Result>()
        for (i in resultData.indices) {
            list.add(mapResultToDomain(resultData[i]))
        }
        return list
    }

    override suspend fun getCountQuestion(testName: String): Int {
        return db.appDao().getQuestionCount(testName)
    }

    private fun mapQuestionToDomain(questionData: com.example.englishease.data.models.Question): Question {
        return Question(
            questionData.question_number,
            questionData.question_text,
            listOf(
                questionData.answer_one,
                questionData.answer_two,
                questionData.answer_three,
                questionData.answer_four
            ),
            listOf(
                questionData.point_one,
                questionData.point_two,
                questionData.point_three,
                questionData.point_four
            )
        )
    }

    private fun mapResultToDomain(resultData: com.example.englishease.data.models.Result): com.example.englishease.domain.models.Result {
        return com.example.englishease.domain.models.Result(
            resultData.test_name,
            resultData.user,
            resultData.points,
            resultData.date
        )
    }



    override suspend fun showTheoryList(): List<String> {
        if (db.appDao().getTheoryList().isEmpty()) {
            Log.d("RRR", "пусто")
            TODO()
        } else {
            Log.d("RRR", "не пусто")
        }
        return db.appDao().getTheoryList()
    }


}