package com.example.englishease.data.repository

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.englishease.data.database.AppDatabase
import com.example.englishease.data.models.Test
import com.example.englishease.data.models.Conclusion
import com.example.englishease.domain.models.Question
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

    override suspend fun showResults(testName: String): List<com.example.englishease.domain.models.Conclusion> {
        val resultData = db.appDao().getResults(testName)
        val list = ArrayList<com.example.englishease.domain.models.Conclusion>()
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

    private fun mapResultToDomain(resultData: com.example.englishease.data.models.Result): com.example.englishease.domain.models.Conclusion {
        return com.example.englishease.domain.models.Conclusion(
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
                    "Nouns are words that give a name to people, places or things, " +
                            "though they can also refer to ideas and other abstract objects. \n" +
                            "Nouns are words that give a name to people, places or things, " +
                            "though they can also refer to ideas and other abstract objects. " +
                    "Nouns can function as a subject, object, complement, appositive, or object " +
                            "of a preposition. One can not imagine a sentence without a noun. " +
                            "It will make no sense. Let us consider an example:\n" +
                            "\n" +
                            "John offered Jane a beautiful bunch of flowers." +
                    "This sentence contains four nouns having different critical grammatical functions:\n" +
                            "\n" +
                            "\"John\" is the subject of the sentence.\n" +
                            "\"Jane\" is the direct object of the verb \"offered\".\n" +
                            "\"Bunch\" is the indirect object of the verb \" offered\".\n" +
                            "\"Flowers\" is the object of the preposition \"of\".",
                    5
                )
            )
            db.appDao().insertTest(
                Test(
                    "The present simple tense",
                    "The simple present tense in English is used to describe an action that is regular, true or normal." +
                            "We use the present tense:\n" +
                            "1. For repeated or regular actions in the present time period.\n" +
                            "I take the train to the office.\n" +
                            "The train to Berlin leaves every hour.\n" +
                            "John sleeps eight hours every night during the week.\n" +
                            "2. For facts.\n" +
                            "The President of The USA lives in The White House.\n" +
                            "A dog has four legs.\n" +
                            "We come from Switzerland.\n" +
                            "3. For habits.\n" +
                            "I get up early every day.\n" +
                            "Carol brushes her teeth twice a day.\n" +
                            "They travel to their country house every weekend.\n" +
                            "4. For things that are always / generally true.\n" +
                            "It rains a lot in winter.\n" +
                            "The Queen of England lives in Buckingham Palace.\n" +
                            "They speak English at work.",
                    5
                )
            )
            db.appDao().insertTest(
                Test(
                    "The past simple tense",
                    "English uses verbs in the simple past tense to refer to actions" +
                            "situations, or events that are finished and that happened before" +
                            "now. There are three ways to form simple past tense in English." +
                            "\n" +
                            "These three different ways depend on the verb:" +
                            "the verb be\n" +
                            "(special forms)\n" +
                            "regular verbs\n" +
                            "(add - d, - ed, or change y to i and add - ed)\n" +
                            "regular verbs\n" +
                            "(special forms)\n" +
                            "There is only one form of each verb in the past simple tense.*\n" +
                            "* The exception is TO BE which has two forms: was and were. We will see more about this later.\n" +
                            "\n" +
                            "The past tense of LIVE is LIVED.\n" +
                            "LIVED is used for all subjects including HE, SHE and IT.\n" +
                            "\n" +
                            "I lived …\n" +
                            "You lived …\n" +
                            "We lived …\n" +
                            "They lived …\n" +
                            "He lived …\n" +
                            "She lived …\n" +
                            "It lived …",
                    5
                )
            )
            db.appDao().insertTest(
                Test(
                    "The future simple tense",
                    "Simple future tense is used when talking about things that will happen " +
                            "at a time later rather than the present time. Sometimes you will hear people " +
                            "call future simple tense will, this is because most of the time you will use " +
                            "the future simple tense with the modal auxiliary will." +
                            "The simple future is formed as follows:\n" +
                            "\n" +
                            "will / 'll + verb\n" +
                            "will = 'll" +
                            "Examples:\n" +
                            "\n" +
                            "I think I'll buy a new computer.\n" +
                            "I will open the door. Someone is ringing the bell." +
                            "\n" +
                    "Will and be going to, can be used, when talking about predictions and guesses.\n" +
                            "\n" +
                            "My new car will be here next week.\n" +
                            "\n" +
                            "My new car is going to be here next week.",
                    5
                )
            )
            db.appDao().insertTest(
                Test(
                    "Modal verbs",
                    "Modals (also called modal verbs, modal auxiliary verbs, modal " +
                            "auxiliaries) are special verbs that behave irregularly in English. " +
                            "They are different from normal verbs like \"work, play, visit...\" " +
                            "They give additional information about the function of the main verb " +
                            "that follows it. They have a great variety of communicative functions.\n" +
                            "Here are some characteristics of modal verbs:\n" +
                            "They never change their form. You can't add \"s\", \"ed\", \"ing\"...\n" +
                            "They are always followed by an infinitive without \"to\" (e.i. the bare infinitive.)\n" +
                            "They are used to indicate modality and allow speakers to express certainty, possibility, " +
                            "willingness, obligation, necessity, ability" +
                    "Here is a list of modal verbs:\n" +
                            "can, could, may, might, will, would, shall, should, must\n" +
                            "The verbs or expressions dare, ought to, had better, and need not " +
                            "behave like modal auxiliaries to a large extent and may be added to " +
                            "the above list",
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
                Conclusion(
                    1,
                    "Nouns",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    2,
                    "Nouns",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    3,
                    "Nouns",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                Conclusion(
                    4,
                    "The present simple tense",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    5,
                    "The present simple tense",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    6,
                    "The present simple tense",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                Conclusion(
                    7,
                    "The past simple tense",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    8,
                    "The past simple tense",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    9,
                    "The past simple tense",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )



            db.appDao().insertTextResult(
                Conclusion(
                    10,
                    "The future simple tense",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    11,
                    "The future simple tense",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    12,
                    "The future simple tense",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                Conclusion(
                    13,
                    "Modal verbs",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    14,
                    "Modal verbs",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
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