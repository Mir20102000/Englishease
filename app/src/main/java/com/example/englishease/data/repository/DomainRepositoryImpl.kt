package com.example.englishease.data.repository

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.englishease.data.database.AppDatabase
import com.example.englishease.data.models.Test
import com.example.englishease.data.models.TextResult
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
            db.appDao().insertTest(
                Test(
                    "Nouns",
                    "Nouns are word used to name general items rather than specific ones. Proper nouns are the names of particular person, place, or thing. A noun that used to denote one thing, one person, one animal, one place and so on called a singular noun.",
                    5
                )
            )
            db.appDao().insertTest(
                Test(
                    "The present simple tense",
                    "We use it when actions happen regularly, sometimes or never; state the facts; we tell the events of a story that is happening now.  Tense form: S+do/does/don't/doesn't+V. Example: He usually sits there.",
                    5
                )
            )
            db.appDao().insertTest(
                Test(
                    "The past simple tense",
                    "We use it when actions happen in a certain moments in the past. Example: I went to university in 2015. The simple past tense is usually made by  adding  -ed to the verb. The rule used to form simple past tense verbs is similar to the rule of adding -s to form Plurals.",
                    5
                )
            )
            db.appDao().insertTest(
                Test(
                    "The future simple tense",
                    "We use it when actions that not happened yet but will happen in the future. We use will/shall+Verb to form the future verbs form. Example: I will miss you when you leave. Sometimes, we talk about a schedule that do not change in the future, however, we use simple tense to form the verbs.",
                    5
                )
            )
            db.appDao().insertTest(
                Test(
                    "Modal verbs",
                    "Modals are different from normal verbs. They don't use an -s for the third person singular. They make questions by inversion. They are followed directly by a verb. Example: She can speak four languages.",
                    5
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    1,
                    "Nouns",
                    1,
                    "The plural of Cat is:",
                    "caties",
                    "cats",
                    "cates",
                    "catis",
                    0,
                    20,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    2,
                    "Nouns",
                    2,
                    "The plular of watch is:",
                    "watch",
                    "watchies",
                    "wathces",
                    "wathches",
                    0,
                    0,
                    0,
                    20
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    3,
                    "Nouns",
                    3,
                    "What's the plural of Mouse?",
                    "Mouses",
                    "Mice",
                    "Mouss",
                    "Mousies",
                    0,
                    20,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    4,
                    "Nouns",
                    4,
                    "What's the plural of fish?",
                    "fish",
                    "fishes",
                    "fishs",
                    "fihies",
                    20,
                    0,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    5,
                    "Nouns",
                    5,
                    "The plural of pen is:",
                    "pencies",
                    "penies",
                    "pens",
                    "pencils",
                    0,
                    0,
                    20,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    6,
                    "The present simple tense",
                    1,
                    "Do you like cats? Yes, I __.",
                    "do",
                    "does",
                    "don't",
                    "doesn't",
                    20,
                    0,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    7,
                    "The present simple tense",
                    2,
                    "Where __ your friend come from?",
                    "are",
                    "is",
                    "do",
                    "does",
                    0,
                    0,
                    0,
                    20
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    8,
                    "The present simple tense",
                    3,
                    "I __ tired at all.",
                    "are",
                    "is",
                    "'m not",
                    "isn't",
                    0,
                    0,
                    20,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    9,
                    "The present simple tense",
                    4,
                    "It __ interesting.",
                    "am",
                    "is",
                    "does",
                    "do",
                    0,
                    20,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    10,
                    "The present simple tense",
                    5,
                    "When __ it start?",
                    "does",
                    "do",
                    "is",
                    "are",
                    20,
                    0,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    11,
                    "The past simple tense",
                    1,
                    "Their results __ much better.",
                    "was",
                    "were",
                    "did",
                    "didn't",
                    0,
                    20,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    12,
                    "The past simple tense",
                    2,
                    "They __ me about it.",
                    "did",
                    "tell",
                    "were",
                    "told",
                    0,
                    0,
                    0,
                    20
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    13,
                    "The past simple tense",
                    3,
                    "Whay kind of offer __ it?",
                    "was",
                    "were",
                    "did",
                    "didn't",
                    20,
                    0,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    14,
                    "The past simple tense",
                    4,
                    "He __ Russia last week",
                    "leave",
                    "left",
                    "leaves",
                    "leaved",
                    0,
                    20,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    15,
                    "The past simple tense",
                    5,
                    "Where __ you?",
                    "was",
                    "did",
                    "were",
                    "weren't",
                    0,
                    0,
                    20,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    16,
                    "The future simple tense",
                    1,
                    "__ you join us?",
                    "are",
                    "is",
                    "will",
                    "were",
                    0,
                    0,
                    20,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    17,
                    "The future simple tense",
                    2,
                    "I am __ pizza for me.",
                    "buy",
                    "will",
                    "buying",
                    "do",
                    0,
                    0,
                    20,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    18,
                    "The future simple tense",
                    3,
                    "How long __ she away?",
                    "will",
                    "do",
                    "going",
                    "are",
                    20,
                    0,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    19,
                    "The future simple tense",
                    4,
                    "The song __ become very popular.",
                    "will do",
                    "will",
                    "was",
                    "were",
                    0,
                    20,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    20,
                    "The future simple tense",
                    5,
                    "He __ to improve his speaking skills.",
                    "is going",
                    "going",
                    "will",
                    "shall",
                    20,
                    0,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    21,
                    "Modal verbs",
                    1,
                    "They have __ something.",
                    "change",
                    "changes",
                    "to change",
                    "to changed",
                    0,
                    0,
                    20,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    22,
                    "Modal verbs",
                    2,
                    "I must __ my speasking skills.",
                    "to improve",
                    "improve",
                    "improved",
                    "to improved",
                    0,
                    20,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    23,
                    "Modal verbs",
                    3,
                    "I must __ the first place.",
                    "take",
                    "to take",
                    "taking",
                    "taken",
                    20,
                    0,
                    0,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    24,
                    "Modal verbs",
                    4,
                    "You need __ it one more time.",
                    "to explaining",
                    "explain",
                    "to explain",
                    "explained",
                    0,
                    0,
                    20,
                    0
                )
            )
            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    25,
                    "Modal verbs",
                    5,
                    "He can __ to help us.",
                    "to come",
                    "come",
                    "coming",
                    "came",
                    0,
                    20,
                    0,
                    0
                )
            )
            db.appDao().insertTextResult(
                TextResult(
                    1,
                    "Nouns",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                TextResult(
                    2,
                    "Nouns",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                TextResult(
                    3,
                    "Nouns",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                TextResult(
                    4,
                    "The present simple tense",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                TextResult(
                    5,
                    "The present simple tense",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                TextResult(
                    6,
                    "The present simple tense",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                TextResult(
                    7,
                    "The past simple tense",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                TextResult(
                    8,
                    "The past simple tense",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                TextResult(
                    9,
                    "The past simple tense",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )



            db.appDao().insertTextResult(
                TextResult(
                    10,
                    "The future simple tense",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                TextResult(
                    11,
                    "The future simple tense",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                TextResult(
                    12,
                    "The future simple tense",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                TextResult(
                    13,
                    "Modal verbs",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                TextResult(
                    14,
                    "Modal verbs",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                TextResult(
                    15,
                    "Modal verbs",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )
        } else {
            Log.d("RRR", "не пусто")
        }
        return db.appDao().getTheoryList()
    }


}