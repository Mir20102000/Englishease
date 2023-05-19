package com.example.englishease.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.englishease.data.models.Question
import com.example.englishease.data.models.Test
import com.example.englishease.data.models.Result
import com.example.englishease.data.models.TextResult

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(test: Test)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTextResult(textResult: TextResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: Result)




    @Query("SELECT theory_text FROM test WHERE test.name == :testName LIMIT 1")
    suspend fun getTheoryText(testName: String): String

    @Query("SELECT * FROM question WHERE question.test_name == :testName AND question_number == :questionNumber LIMIT 1")
    suspend fun getQuestionInfo(testName: String, questionNumber: Int): Question

    @Query("SELECT * FROM result WHERE result.test_name == :testName ORDER BY result_id DESC")
    suspend fun getResults(testName: String): List<Result>

    @Query("SELECT name FROM test")
    suspend fun getTheoryList(): List<String>

    @Query("SELECT question_count FROM test WHERE test.name == :testName LIMIT 1")
    suspend fun getQuestionCount(testName: String): Int

    @Query("SELECT result_id FROM result WHERE result.test_name == :testName ORDER BY result_id DESC LIMIT 1")
    suspend fun getResultId(testName: String): Int

    @Query("SELECT text FROM text_result WHERE testName == :testName AND beginPoint <= :points AND endPoint >= :points LIMIT 1")
    suspend fun getResult(testName: String, points: Int): String

}
