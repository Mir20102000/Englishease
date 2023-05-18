package com.example.englishease.data.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.englishease.data.models.Question
import com.example.englishease.data.models.Test
import com.example.englishease.data.models.Theory
import com.example.englishease.data.models.Result

interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheory(theory: Theory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(test: Test)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: Result)


    // Вывод списка теорий
    @Query("SELECT theory_name FROM theory")
    suspend fun getTheoryList(): List<String>

    // Вывод списка тестов (showTestsList)
    @Query("SELECT test_name FROM test")
    suspend fun getTestsList(): List<String>

    // Вывод списка для результатов тестов (showTestResultsList)
    @Query("SELECT result_name FROM result")
    suspend fun getTestsResultsList(): List<String>


    // вывод выбранной теории (меняем значение theory_name в зависимости от выбранной теории) (showSelectedTheory)
    @Query("SELECT theory_name, theory_text FROM theory WHERE theory_name= :theoryName LIMIT 1")
    suspend fun getSelectedTheory(theoryName: String): Theory

    // начать выбранный тест (beginTest) продолжить (continueTest) и закончить тест (finishTest)
    @Query("SELECT * FROM question WHERE test_name == :test_name AND question_number == :questionNumber LIMIT 1")
    suspend fun getQuestionInfo(test_name: String, questionNumber: Int): Question

    // вывод результата только что пройденного теста (меняем значения user и test_id) (finishTest)
    @Query("SELECT user, points, date FROM result where result.test_name == :testName LIMIT 1")
    suspend fun getTestResult(testName: String): String

    // вывести результаты для выбранного теста(меняем значение test_id в зависимости от теста) (showSelectedTestResults)
    @Query("SELECT * FROM result WHERE test_name == :testName")
    suspend fun getSelectedTestResults(testName: String): List<String>

}
