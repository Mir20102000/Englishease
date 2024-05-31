package com.example.englishease.domain.usecases

import com.example.englishease.domain.models.Question
import com.example.englishease.domain.models.Conclusion
import com.example.englishease.domain.repository.DomainRepository

//import ru.studyguk.testingsystem.domain.models.Question
//import ru.studyguk.testingsystem.domain.models.TextResult
//import ru.studyguk.testingsystem.domain.repository.TestRepository

class SaveTestUseCase(private val testRepository: DomainRepository) {
    suspend fun saveTest(
        testName: String,
        declTest: String,
        queCount: Int,
        questionList: List<Question>,
        textResultList: List<Conclusion>
    ) {
        testRepository.saveTest(testName, declTest, queCount, questionList, textResultList)
    }
}