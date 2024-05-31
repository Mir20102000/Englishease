package com.example.englishease.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishease.domain.models.Conclusion
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.usecases.SaveTestUseCase
import kotlinx.coroutines.launch

class CreateTestViewModel(private val saveTestUseCase: SaveTestUseCase) : ViewModel() {
    private val _testName = MutableLiveData<String>()
    val testName: LiveData<String> = _testName

    private val _declTest = MutableLiveData<String>()
    val declTest: LiveData<String> = _declTest

    private val _queCount = MutableLiveData<Int>()
    val queCount: LiveData<Int> = _queCount

    private val _questionList = MutableLiveData<List<Question>>()
    var questionList: LiveData<List<Question>> = _questionList

    private val _curNum = MutableLiveData<Int>()
    var curNum: LiveData<Int> = _curNum

    private val _textResultList = MutableLiveData<List<Conclusion>>()
    var textResultList: LiveData<List<Conclusion>> = _textResultList

    fun saveTestTitle(testName: String, queCount: Int) {
        _testName.value = testName
        _queCount.value = queCount
    }

    fun resetCurNum() {
        _curNum.value = 0
    }

    fun updateCurNum(diff: Int) {
        _curNum.value = _curNum.value?.plus(diff)
    }

    fun saveQuestions(
        questions: List<String>,
        answers: List<String>,
        answers2: List<String>,
        answers3: List<String>,
        points: List<Int>,
        points2: List<Int>,
        points3: List<Int>,
    ) {
        val list = mutableListOf<Question>()
        for (i in questions.indices) {
            if (questions[i] == "") break
            list.add(
                Question(
                    i + 1,
                    questions[i],
                    listOf(answers[i], answers2[i], answers3[i]),
                    listOf(points[i], points2[i], points3[i])
                )
            )
        }
        _questionList.value = list
    }

    fun saveTextResults(
        textResults: List<String>,
        borders: List<Int>
    ) {
        val list = mutableListOf<Conclusion>()
        for (i in textResults.indices) {
            if (textResults[i] == "") break
            list.add(
                Conclusion(
                    borders[i],
                    borders[i + 1],
                    textResults[i]
                )
            )
        }
        _textResultList.value = list
    }

    fun saveTest() {
        viewModelScope.launch {
            testName.value?.let {
                declTest.value?.let { it1 ->
                    queCount.value?.let { it2 ->
                        questionList.value?.let { it3 ->
                            textResultList.value?.let { it4 ->
                                saveTestUseCase.saveTest(
                                    it,
                                    it1, it2, it3, it4
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}