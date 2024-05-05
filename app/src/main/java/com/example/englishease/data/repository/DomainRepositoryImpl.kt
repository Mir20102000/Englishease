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
        return db.appDao().getTheoryList()
    }

    override suspend fun showLessonsList(): List<String> {
        if (db.appDao().getLessonsList().isEmpty()) {
            Log.d("LLL", "пусто")
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Adjectives and prepositions",
                    description = "Do you know how to use adjectives with prepositions like interested in or similar to?",
                    content = "        Some adjectives go with certain prepositions. There are no grammatical\n" +
                            "        rules for which preposition is used with which adjective, so it\\'s a good idea to try to\n" +
                            "        learn them together. To help you do this, write new vocabulary in your notebook in a\n" +
                            "        sentence or phrase.\n" +
                            "\n" +
                            "        \\n\\nHowever, there are some patterns that can help you. Let\\'s look at them first.\n" +
                            "        Remember that a preposition is followed by a noun or a gerund (-ing form).\n" +
                            "        \\n\\nWith at\n" +
                            "        \\nWe use at with adjectives like good/bad/amazing/brilliant/terrible, etc. to talk about\n" +
                            "        skills and abilities.\n" +
                            "        \\nHe\\'s really good at English.\n" +
                            "        \\nShe\\'s amazing at the piano.\n" +
                            "        \\nThey\\'re terrible at organising anything.\n" +
                            "        \\nI\\'m not very good at drawing.\n" +
                            "        \\n\\nWith about\n" +
                            "        \\nWe often use about with adjectives of feelings like angry/excited/happy/nervous/sad/\n" +
                            "        stressed/worried,etc. to explain what is causing that feeling.\n" +
                            "        \\nI\\'m angry about the decision.\n" +
                            "        \\nHe\\'s nervous about the presentation.\n" +
                            "        \\nShe\\'s excited about the new job.\n" +
                            "        \\nThey were worried about the exam.\n" +
                            "        \\n\\nWith of\n" +
                            "        \\nHowever, sometimes we use of with feelings.\n" +
                            "        \\nShe was afraid of telling her mum.\n" +
                            "        \\nI\\'m frightened of having an accident.\n" +
                            "        \\nHe\\'s scared of flying.\n" +
                            "        \\nYou should be proud of your progress.\n" +
                            "        \\n\\nWith to\n" +
                            "        \\nWe can use to to show the connection between people or things.\n" +
                            "        \\nHe\\'s married to the director.\n" +
                            "        \\nI\\'m addicted to my phone.\n" +
                            "        \\nI\\'m allergic to nuts. It\\'s similar to the old one.\n" +
                            "        \\n We can also use to to talk about someone\\'s behaviour towards someone else.\n" +
                            "        \\nThey were really friendly to me.\n" +
                            "        \\nWas he nice to you?\n" +
                            "        \\nHe is always polite to everyone.\n" +
                            "        \\nShe was very rude to the waitress.\n" +
                            "        \\n\\nHere are some other useful adjectives with prepositions.\n" +
                            "        \\n\\nWith for\n" +
                            "        \\nExercise is good for you.\n" +
                            "        \\nStress is bad for you.\n" +
                            "        \\nThe town is famous for its cheese.\n" +
                            "        \\nI\\'m responsible for the financial side of the business.\n" +
                            "        \\nWith in She\\'s interested in the project.\n" +
                            "        \\nThey want someone who\\'s experienced in design.\n" +
                            "        \\nI didn\\'t want to get involved in the argument."
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Adjectives ending in '-ed' and '-ing'",
                    description = "Do you know the difference between bored and boring?",
                    content = "Adjectives that end in -ed (e.g. bored, interested) and adjectives that end in -ing (e.g.\n" +
                            "        boring, interesting) are often confused.\n" +
                            "\n" +
                            "        \\n\\n-ed adjectives\n" +
                            "\n" +
                            "        \\nAdjectives that end in -ed generally describe emotions – they tell us how people feel.\n" +
                            "\n" +
                            "        \\n\\nI was so bored in that lesson, I almost fell asleep.\n" +
                            "        \\nHe was surprised to see Helen after all those years.\n" +
                            "        \\nShe was really tired and went to bed early.\n" +
                            "\n" +
                            "        \\n\\n-ing adjectives\n" +
                            "\n" +
                            "        \\n\\nAdjectives that end in -ing generally describe the thing that causes the emotion – a boring lesson makes you feel bored.\n" +
                            "\n" +
                            "        \\n\\nHave you seen that film? It\\'s really frightening.\n" +
                            "        \\nI could listen to her for hours. She\\'s so interesting.\n" +
                            "        \\nI can\\'t sleep! That noise is really annoying!\n" +
                            "\n" +
                            "        \\n\\nHere are some adjectives that can have both an -ed and an -ing form.\n" +
                            "        \\nannoyed         \\t\\t\\tannoying\n" +
                            "        \\nbored           \\t\\t\\tboring\n" +
                            "        \\nconfused        \\t\\t\\tconfusing\n" +
                            "        \\ndisappointed    \\t\\t\\tdisappointing\n" +
                            "        \\nexcited         \\t\\t\\texciting\n" +
                            "        \\nfrightened      \\t\\t\\tfrightening\n" +
                            "        \\ninterested      \\t\\t\\tinteresting\n" +
                            "        \\nsurprised       \\t\\t\\tsurprising\n" +
                            "        \\ntired           \\t\\t\\ttiring\n" +
                            "        \\nworried         \\t\\t\\tworrying"
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Articles: 'a', 'an', 'the'",
                    description = "Do you know how to use a, an and the?",
                    content = "Singular, countable nouns always have an article – a/an or the (or another determiner –\n" +
                            "        my, your, this, that, etc.).\n" +
                            "\n" +
                            "        \\nWe use a/an – the indefinite article – when we talk about something for the first time, or something that is part of a group or type.\n" +
                            "\n" +
                            "        \\n\\nI saw a good film yesterday.\n" +
                            "        \\nDo you want a drink?\n" +
                            "\n" +
                            "        \\n\\nWe use a when the word that follows it begins with a consonant sound. We use an when\n" +
                            "        it\\'s followed by a vowel sound. This makes pronunciation easier.\n" +
                            "\n" +
                            "        \\n\\nShe has a university degree.\n" +
                            "        \\nIt took me an hour to get home.\n" +
                            "\n" +
                            "        \\n\\nWe use the – the definite article – when the listener already knows which thing we are\n" +
                            "        talking about because it was mentioned before or because there\\'s only one of them.\n" +
                            "\n" +
                            "        \\n\\nI\\'m going to take the dog for a walk.\n" +
                            "        \\nHave you seen the car key?\n" +
                            "        \\nThey go to the school next to the bridge.\n" +
                            "\n" +
                            "        \\n\\nThings in general\n" +
                            "\n" +
                            "        \\n\\nWhen we talk about things in general, we normally use a plural or uncountable noun with\n" +
                            "        no article.\n" +
                            "\n" +
                            "        \\n\\nBirds eat worms.\n" +
                            "        \\nWater freezes at 0°C.\n" +
                            "        \\nChildren need a lot of sleep.\n" +
                            "\n" +
                            "        \\n\\nParticular groups of things\n" +
                            "\n" +
                            "        \\n\\nWhen we talk about a particular group of things, we use the.\n" +
                            "\n" +
                            "        \\n\\nWe went to the zoo and saw the kangaroos. (These are the particular kangaroos in that\n" +
                            "        zoo – not kangaroos in general.)"
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Articles: 'the' or no article",
                    description = "Do you know when you need to use the in common phrases and place names?",
                    content = "We don\\'t usually use an article in expressions with bed, work and home.\n" +
                            "\n" +
                            "        \\n\\ngo to bed / be in bed\n" +
                            "        \\ngo to work / be at work / start work / finish work\n" +
                            "        \\ngo home / be at home / get home / stay at home\n" +
                            "\n" +
                            "        \\nWe also don\\'t normally use an article in expressions with school, university, prison and\n" +
                            "        hospital.\n" +
                            "\n" +
                            "        \\n\\nstart school / go to school / be at school\n" +
                            "        \\ngo to university / be at university\n" +
                            "        \\nbe sent to prison / go to prison / be in prison\n" +
                            "        \\ngo to hospital / be in hospital\n" +
                            "\n" +
                            "        \\n\\nBut we usually use the if someone is just visiting the place, and not there as a\n" +
                            "        student/prisoner/patient, etc.\n" +
                            "\n" +
                            "        \\n\\nMy son has started school now. I went to the school to meet his teacher.\n" +
                            "        \\nI went to the prison a lot when I was a social worker.\n" +
                            "        \\nI\\'m at the hospital. My sister has just had a baby.\n" +
                            "\n" +
                            "        \\n\\nPlace names\n" +
                            "\n" +
                            "        \\n\\nWe don\\'t normally use an article for continents, most countries, cities, towns, lakes,\n" +
                            "        mountains or universities. So, we say:\n" +
                            "\n" +
                            "        \\n\\nAfrica, Asia, Europe\n" +
                            "        \\nIndia, Ghana, Peru, Denmark\n" +
                            "        \\nAddis Ababa, Hanoi, New York, Moscow\n" +
                            "        \\nLake Victoria, Lake Superior, Lake Tanganyika\n" +
                            "        \\nMount Everest, Mount Kilimanjaro, Mount Elbrus\n" +
                            "        \\nCardiff University, Harvard University, Manchester University\n" +
                            "\n" +
                            "        \\n\\nSome countries are different. Country names with United have the. There are other\n" +
                            "        countries which are exceptions too. So, we say:\n" +
                            "\n" +
                            "        \\n\\nthe United Arab Emirates, the United Kingdom, the United States of America\n" +
                            "        the Bahamas, the Gambia\n" +
                            "\n" +
                            "        \\n\\nSeas and oceans, mountain ranges and rivers have the:\n" +
                            "\n" +
                            "        \\n\\nthe Atlantic, the Pacific, the Mediterranean\n" +
                            "        \\nthe Andes, the Himalayas, the Alps\n" +
                            "        \\nthe Nile, the Amazon, the Yangtze\n" +
                            "\n" +
                            "        \\n\\nUniversities with of in the title also have the:\n" +
                            "\n" +
                            "        \\n\\nthe University of Cape Town, the University of Delhi, the University of Tokyo"
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Comparative adjectives",
                    description = "Do you know when you need to use the in common phrases and place names?",
                    content = "We use comparative adjectives to compare two things or show change. The comparative form\n" +
                            "        depends on the number of syllables in the adjective.\n" +
                            "\n" +
                            "        \\n\\nAdjectives with one syllable\n" +
                            "\n" +
                            "        \\n\\nTo make comparative forms with one-syllable adjectives, we usually add -er:\n" +
                            "\n" +
                            "        \\n\\nold → older\n" +
                            "        \\nclean → cleaner\n" +
                            "        \\nslow → slower\n" +
                            "\n" +
                            "        \\n\\nIf an adjective ends in -e, we add -r:\n" +
                            "\n" +
                            "        \\n\\nsafe → safer\n" +
                            "        \\nnice → nicer\n" +
                            "\n" +
                            "        \\n\\nIf an adjective ends in a vowel and a consonant, we usually double the consonant:\n" +
                            "\n" +
                            "        \\n\\nbig → bigger\n" +
                            "        \\nhot → hotter\n" +
                            "\n" +
                            "        \\n\\nAdjectives with two or more syllables\n" +
                            "\n" +
                            "        \\n\\nIf a two-syllable adjective ends in a consonant and -y, we change -y to -i and add -er:\n" +
                            "\n" +
                            "        \\n\\nnoisy → noisier\n" +
                            "        \\nhappy → happier\n" +
                            "        \\neasy → easier\n" +
                            "\n" +
                            "        \\n\\nWe use more to make comparative forms for most other two-syllable adjectives and for all\n" +
                            "        adjectives with three or more syllables:\n" +
                            "\n" +
                            "        \\n\\ncrowded → more crowded\n" +
                            "        \\nstressful → more stressful\n" +
                            "        \\ndangerous → more dangerous\n" +
                            "\n" +
                            "        \\n\\nException: You can either add -er/-r or use more with some two-syllable adjectives, such\n" +
                            "        as common, cruel, gentle, handsome, likely, narrow, pleasant, polite, simple and stupid.\n" +
                            "\n" +
                            "        \\n\\nI think life in the countryside is simpler than in the city.\n" +
                            "        \\nIt\\'s more simple to live in the city because everything you need is there.\n" +
                            "\n" +
                            "        \\n\\nIrregular adjectives\n" +
                            "\n" +
                            "        \\n\\nThe adjectives good, bad and far have irregular comparative forms:\n" +
                            "\n" +
                            "        \\n\\ngood → better\n" +
                            "        \\nbad → worse\n" +
                            "        \\nfar → further/farther\n" +
                            "\n" +
                            "        \\n\\nThan\n" +
                            "\n" +
                            "        \\n\\nWhen we want to say which person or thing we are comparing with, we can use than:\n" +
                            "\n" +
                            "        \\n\\nTheir house is cleaner than ours.\n" +
                            "        \\nTraffic is slower in the city than in the countryside.\n" +
                            "        \\nAfter the race I was more tired than Anne."
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Infinitive of purpose",
                    description = "Do you know how and when to use an infinitive to talk about purpose?",
                    content = "When we want to answer the question why?, we can use an infinitive of purpose. Most of the\n" +
                            "        time, we use the to-infinitive.\n" +
                            "\n" +
                            "        \\n\\nWhy do you have the LearnEnglish Grammar app?\n" +
                            "        \\nTo improve my grammar.\n" +
                            "\n" +
                            "        \\n\\nWhy do we always eat at home?\n" +
                            "        \\nWe eat at home to save money.\n" +
                            "\n" +
                            "        \\n\\nWhy do you have a bicycle?\n" +
                            "        \\nI use it to go to work.\n" +
                            "\n" +
                            "        \\n\\nWhy does she run every morning?\n" +
                            "        \\nShe runs to keep fit."
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Nouns: countable and uncountable",
                    description = "Do you know how to use a, some, any, much and many?",
                    content = "Nouns can be countable or uncountable. Countable nouns can be counted, e.g. an apple, two\n" +
                            "        apples, three apples, etc. Uncountable nouns cannot be counted, e.g. air, rice, water, etc.\n" +
                            "        When you learn a new noun, you should check if it is countable or uncountable and note how\n" +
                            "        it is used in a sentence.\n" +
                            "\n" +
                            "        \\n\\nCountable nouns\n" +
                            "\n" +
                            "        \\n\\nFor positive sentences we can use a/an for singular nouns or some for plurals.\n" +
                            "\n" +
                            "        \\n\\nThere\\'s a man at the door.\n" +
                            "        \\nI have some friends in New York.\n" +
                            "\n" +
                            "        \\n\\nFor negatives we can use a/an for singular nouns or any for plurals.\n" +
                            "\n" +
                            "        \\n\\nI don\\'t have a dog.\n" +
                            "        \\nThere aren\\'t any seats.\n" +
                            "\n" +
                            "        \\n\\nUncountable nouns\n" +
                            "\n" +
                            "        \\n\\nHere are some examples of uncountable nouns:\n" +
                            "        \\n\\nbread \trice \tcoffee \tinformation\n" +
                            "        \\n\\nmoney \tadvice \tluggage \tfurniture\n" +
                            "\n" +
                            "        \\n\\nWe use some with uncountable nouns in positive sentences and any with negatives.\n" +
                            "\n" +
                            "        \\n\\nThere\\'s some milk in the fridge.\n" +
                            "        \\nThere isn\\'t any coffee.\n" +
                            "\n" +
                            "        \\n\\nQuestions\n" +
                            "\n" +
                            "        \\n\\nIn questions we use a/an, any or how many with countable nouns.\n" +
                            "\n" +
                            "        \\n\\nIs there an email address to write to?\n" +
                            "        \\nAre there any chairs?\n" +
                            "        \\nHow many chairs are there?\n" +
                            "\n" +
                            "        \\n\\nAnd we use any or how much with uncountable nouns.\n" +
                            "\n" +
                            "        \\n\\nIs there any sugar?\n" +
                            "        \\nHow much orange juice is there?\n" +
                            "\n" +
                            "        \\n\\nBut when we are offering something or asking for something, we normally use some.\n" +
                            "\n" +
                            "        \\n\\nDo you want some chocolate?\n" +
                            "        \\nCan we have some more chairs, please?\n" +
                            "\n" +
                            "        \\n\\nWe also use some in a question when we think the answer will be \\'yes\\'.\n" +
                            "\n" +
                            "        \\n\\nHave you got some new glasses?\n" +
                            "\n" +
                            "        \\n\\nOther expressions of quantity\n" +
                            "\n" +
                            "        \\n\\nA lot of (or lots of) can be used with both countable and uncountable nouns.\n" +
                            "\n" +
                            "        \\n\\nThere are lots of apples on the trees.\n" +
                            "        \\nThere is a lot of snow on the road.\n" +
                            "\n" +
                            "        \\n\\nNotice that we don\\'t usually use many or much in positive sentences. We use a lot of\n" +
                            "        instead.\n" +
                            "\n" +
                            "        \\n\\nThey have a lot of money.\n" +
                            "\n" +
                            "        \\n\\nHowever, in negative sentences we use not many with countable nouns and not much with\n" +
                            "        uncountable nouns.\n" +
                            "\n" +
                            "        \\n\\nThere are a lot of carrots but there aren\\'t many potatoes.\n" +
                            "        \\nThere\\'s lots of juice but there isn\\'t much water."
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Past continuous and past simple",
                    description = "Do you know how to use the past continuous and past simple?",
                    content = "The past continuous and the past simple help us to show how two past actions or situations\n" +
                            "        are connected.\n" +
                            "\n" +
                            "        \\n\\nPast simple\n" +
                            "\n" +
                            "        \\n\\nThe past simple shows us that an action was in the past, not in the present. Regular\n" +
                            "        past simple verbs have -ed at the end (e.g. called, played, arrived). Irregular verbs have\n" +
                            "        a different form, usually with a different vowel sound (e.g. wake → woke, break → broke,\n" +
                            "        feel → felt).\n" +
                            "\n" +
                            "        \\n\\nMy parents called me yesterday.\n" +
                            "        \\nI woke up early this morning.\n" +
                            "        \\nSam played basketball when he was at university.\n" +
                            "\n" +
                            "        \\n\\nWe make the negative with didn\\'t and the infinitive verb.\n" +
                            "\n" +
                            "        \\n\\nMy parents didn\\'t call me yesterday.\n" +
                            "        \\nI didn\\'t wake up early this morning.\n" +
                            "\n" +
                            "        \\n\\nWe make the question form with did and then the subject and infinitive verb.\n" +
                            "\n" +
                            "        \\n\\nDid you wake up early this morning?\n" +
                            "        \\nDid Sam play basketball when he was at university?\n" +
                            "\n" +
                            "        \\n\\nPast continuous\n" +
                            "\n" +
                            "        \\n\\nThe past continuous shows us that the action was already in progress at a certain time\n" +
                            "        in the past.\n" +
                            "\n" +
                            "        \\n\\nWhat were you doing at 8 p.m. last night? I was studying.\n" +
                            "\n" +
                            "        \\n\\nThis means that I started studying before 8 p.m. and I continued after 8 p.m.\n" +
                            "\n" +
                            "        \\n\\nThe past continuous can also show that an activity was in progress for some time, not\n" +
                            "        just for a moment.\n" +
                            "\n" +
                            "        \\n\\nWe were cleaning the house all morning.\n" +
                            "\n" +
                            "        \\n\\nWe make the past continuous with was or were and the -ing form of the verb.\n" +
                            "\n" +
                            "        \\n\\nShe couldn\\'t come to the party. She was working.\n" +
                            "        \\nThree years ago, we were living in my home town.\n" +
                            "        \\nI tried to give him some advice, but he wasn\\'t listening.\n" +
                            "        \\nWhat were you doing this time last year?\n" +
                            "\n" +
                            "        \\n\\nPast continuous and past simple\n" +
                            "\n" +
                            "        \\n\\nWhen we use these two tenses together, it shows us that the past simple action happened\n" +
                            "        in the middle of the past continuous action, while it was in progress.\n" +
                            "\n" +
                            "        \\n\\nWhile I was studying, I suddenly felt sleepy.\n" +
                            "\n" +
                            "        \\n\\nWe often use these tenses to show an action interrupting another action.\n" +
                            "\n" +
                            "        \\n\\nI broke my leg when I was skiing.\n" +
                            "        \\nAs I was going to work, I saw an old friend.\n" +
                            "        \\nWe were watching television when the power went off.\n" +
                            "\n" +
                            "        \\n\\nCan you see a difference in the meaning of these two sentences?\n" +
                            "\n" +
                            "        \\n\\nWhen the guests arrived, Jane was cooking dinner.\n" +
                            "        \\nWhen the guests arrived, Jane cooked dinner.\n" +
                            "\n" +
                            "        \\n\\nIn the first one, Jane started cooking dinner before the guests arrived. We know that\n" +
                            "        because it uses the past continuous. In the second sentence, the guests arrived first and\n" +
                            "        then Jane started cooking."
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Possessive 's",
                    description = "Do you know how to use possessive \\'s?",
                    content = "We can use possessive \\'s to talk about the relationship between people or to say who owns\n" +
                            "        something. Possessive \\'s always comes after a noun or a name.\n" +
                            "\n" +
                            "        \\n\\nWe often use possessive \\'s or s\\' when we talk about family and friends.\n" +
                            "\n" +
                            "        \\n\\nGrandma and Grandpa are my mum\\'s parents.\n" +
                            "        \\nMaria\\'s best friend is Juanita.\n" +
                            "        \\nMy cousins\\' birthdays are both in January.\n" +
                            "\n" +
                            "        \\n\\nWe can also use it to say that something belongs to someone.\n" +
                            "\n" +
                            "        \\n\\nThat\\'s Roberto\\'s flat. He\\'s got a flat in the city centre.\n" +
                            "        \\nKim\\'s hair is very long. She\\'s got long, black hair.\n" +
                            "        \\nMy parents\\' garden is beautiful. They grow a lot of flowers.\n" +
                            "\n" +
                            "        \\n\\nWhen it is one person who owns something, we usually use \\'s.\n" +
                            "\n" +
                            "        \\n\\nOur friend\\'s car is red. She loves it.\n" +
                            "        \\nSimon\\'s phone is new. He bought it yesterday.\n" +
                            "        \\nCan you see Amira\\'s keys? She can\\'t find them.\n" +
                            "\n" +
                            "        \\n\\nIt is possible to use more than one possessive in a phrase.\n" +
                            "\n" +
                            "        \\n\\nWe had lunch at my friend\\'s father\\'s house.\n" +
                            "\n" +
                            "        \\n\\nIf a name or noun ends in s, we can add either \\' or \\'s. The pronunciation can be\n" +
                            "        /zɪz/ or /sɪz/.\n" +
                            "\n" +
                            "        \\n\\nIs that James\\' bag?\n" +
                            "        \\nThat\\'s my boss\\'s office.\n" +
                            "\n" +
                            "        \\n\\nIf the thing belongs to more than one person, we usually add \\' after the s of the\n" +
                            "        plural noun.\n" +
                            "\n" +
                            "        \\n\\nOur friends\\' house is in the mountains. They moved there last year.\n" +
                            "        \\nMy grandparents\\' dog is called Bertie.\n" +
                            "        \\nThe twins\\' school is closed today.\n" +
                            "\n" +
                            "        \\n\\nIf the plural noun is irregular and doesn\\'t end in s, we add \\'s.\n" +
                            "\n" +
                            "        \\n\\nThis is our children\\'s school.\n" +
                            "        \\nThe women\\'s clothes are on the second floor.\n" +
                            "        \\nA lot of people\\'s eyes are brown.\n" +
                            "\n" +
                            "        \\n\\nIf something belongs to more than one person, and we give a list of names, the \\'s comes\n" +
                            "        after the last name in the list.\n" +
                            "\n" +
                            "        \\n\\nLiam is Anne and Gary\\'s son.\n" +
                            "\n" +
                            "        \\n\\nRemember that s at the end of a word without an apostrophe (\\') can make it plural, but\n" +
                            "        this doesn\\'t show possession.\n" +
                            "\n" +
                            "        \\nI\\'ve got two brothers.\n" +
                            "        \\nAre those your keys?"
                )
            )
            db.appDao().insertLesson(
                com.example.englishease.data.models.Lesson(
                    name = "Prepositions of place – 'in', 'on', 'at'",
                    description = "Do you know how to use in, on and at to talk about location?",
                    content = "We can use the prepositions in, on and at to say where things are. They go before nouns.\n" +
                            "\n" +
                            "        \\n\\nI am in the kitchen.\n" +
                            "        \\nMy dog likes sleeping on the sofa.\n" +
                            "        \\nThe children eat lunch at school.\n" +
                            "\n" +
                            "        \\n\\nin\n" +
                            "\n" +
                            "        \\n\\nWe use in to talk about a place that is inside a bigger space, such as a box, a house,\n" +
                            "        a city or a country.\n" +
                            "\n" +
                            "        \\n\\nThe clothes are in the wardrobe.\n" +
                            "        \\nThe children are playing in the park.\n" +
                            "        \\nThere\\'s a bookshop in the shopping centre.\n" +
                            "        \\nMy grandmother was born in Sweden.\n" +
                            "\n" +
                            "        \\n\\nWe also use in with other physical locations such as:\n" +
                            "\n" +
                            "        \\n\\nin the world\n" +
                            "        \\nin water / the sea / a river / a lake / a pool\n" +
                            "        \\nin the mountains / the countryside / a valley / the forest\n" +
                            "        \\nin a car / a taxi\n" +
                            "\n" +
                            "        \\n\\non\n" +
                            "\n" +
                            "        \\n\\nWe use on to talk about location on a surface.\n" +
                            "\n" +
                            "        \\n\\nThe books are on the desk.\n" +
                            "        \\nWe live on the fifth floor.\n" +
                            "        \\nThere are pictures on the wall.\n" +
                            "        \\nShe likes to sit on the floor.\n" +
                            "\n" +
                            "        \\n\\nWe also use on for some types of public transport.\n" +
                            "\n" +
                            "        \\n\\nHe\\'s on the bus now.\n" +
                            "        \\nYou can\\'t make phone calls on a plane.\n" +
                            "        \\nThey go to school on the train.\n" +
                            "\n" +
                            "        \\n\\nWe also use on for lines (including rivers, borders, streets, etc.) and islands.\n" +
                            "\n" +
                            "        \\n\\nLondon is on the River Thames.\n" +
                            "        \\nThe Pyrenees are on the border of Spain and France.\n" +
                            "        \\nThere\\'s a market on James Street.\n" +
                            "        \\nI\\'d love to live on the Isle of Wight.\n" +
                            "\n" +
                            "        \\n\\nat\n" +
                            "\n" +
                            "        \\n\\nWe use at in many common phrases, especially when we are talking about a place for a\n" +
                            "        specific activity.\n" +
                            "\n" +
                            "        \\n\\nI\\'m at work.\n" +
                            "        \\nShe\\'s working at home today.\n" +
                            "        \\nThe children are at school.\n" +
                            "        \\nSee you at the train station!\n" +
                            "        \\nThey\\'re at the supermarket.\n" +
                            "        \\nI met him at a party.\n" +
                            "\n" +
                            "        \\n\\nWe also use at for addresses or exact positions.\n" +
                            "\n" +
                            "        \\n\\nI live at 15 Craig Street.\n" +
                            "        \\nShe\\'s sitting at a desk.\n" +
                            "        \\nHe\\'s waiting at the entrance.\n" +
                            "        \\nPlease sit at the back of the room."
                )
            )

            db.appDao().insertTest(
                com.example.englishease.data.models.Test(
                    name = "Adjectives and prepositions",
                    theory_text = "",
                    question_count = 5
                )
            )
            db.appDao().insertTest(
                com.example.englishease.data.models.Test(
                    name = "Adjectives ending in '-ed' and '-ing'",
                    theory_text = "",
                    question_count = 5
                )
            )
            db.appDao().insertTest(
                com.example.englishease.data.models.Test(
                    name = "Articles: 'a', 'an', 'the'",
                    theory_text = "",
                    question_count = 5
                )
            )
            db.appDao().insertTest(
                com.example.englishease.data.models.Test(
                    name = "Articles: 'the' or no article",
                    theory_text = "",
                    question_count = 5
                )
            )
            db.appDao().insertTest(
                com.example.englishease.data.models.Test(
                    name = "Comparative adjectives",
                    theory_text = "",
                    question_count = 5
                )
            )

            db.appDao().insertQuestion(
                com.example.englishease.data.models.Question(
                    1,
                    "Adjectives and prepositions",
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
                    "Adjectives and prepositions",
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
                    "Adjectives and prepositions",
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
                    "Adjectives and prepositions",
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
                    "Adjectives and prepositions",
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
                    "Adjectives ending in '-ed' and '-ing'",
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
                    "Adjectives ending in '-ed' and '-ing'",
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
                    "Adjectives ending in '-ed' and '-ing'",
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
                    "Adjectives ending in '-ed' and '-ing'",
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
                    "Adjectives ending in '-ed' and '-ing'",
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
                    "Articles: 'a', 'an', 'the'",
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
                    "Articles: 'a', 'an', 'the'",
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
                    "Articles: 'a', 'an', 'the'",
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
                    "Articles: 'a', 'an', 'the'",
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
                    "Articles: 'a', 'an', 'the'",
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
                    "Articles: 'the' or no article",
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
                    "Articles: 'the' or no article",
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
                    "Articles: 'the' or no article",
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
                    "Articles: 'the' or no article",
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
                    "Articles: 'the' or no article",
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
                    "Comparative adjectives",
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
                    "Comparative adjectives",
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
                    "Comparative adjectives",
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
                    "Comparative adjectives",
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
                    "Comparative adjectives",
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
                    "Adjectives and prepositions",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    2,
                    "Adjectives and prepositions",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    3,
                    "Adjectives and prepositions",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                Conclusion(
                    4,
                    "Adjectives ending in '-ed' and '-ing'",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    5,
                    "Adjectives ending in '-ed' and '-ing'",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    6,
                    "Adjectives ending in '-ed' and '-ing'",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                Conclusion(
                    7,
                    "Articles: 'a', 'an', 'the'",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    8,
                    "Articles: 'a', 'an', 'the'",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    9,
                    "Articles: 'a', 'an', 'the'",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )



            db.appDao().insertTextResult(
                Conclusion(
                    10,
                    "Articles: 'the' or no article",
                    -1,
                    21,
                    "You should try it one more time."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    11,
                    "Articles: 'the' or no article",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    12,
                    "Articles: 'the' or no article",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )


            db.appDao().insertTextResult(
                Conclusion(
                    13,
                    "Comparative adjectives",
                    -1,
                    21,
                    "Comparative adjectives."
                )
            )
            db.appDao().insertTextResult(
                Conclusion(
                    14,
                    "Comparative adjectives",
                    22,
                    61,
                    "Your results are not bad. But there is a room to improve."
                )
            )

            db.appDao().insertTextResult(
                Conclusion(
                    15,
                    "Comparative adjectives",
                    62,
                    101,
                    "Congratulations! Your results are good. Keep it going!"
                )
            )

        } else {
            Log.d("LLL", "не пусто")
        }
        return db.appDao().getLessonsList()
    }


}