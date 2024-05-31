package com.example.englishease.presentation.viewmodel

import android.util.Log
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
        Log.d("TTT", "_testName.value = " + _testName.value + " _queCount.value = " + _queCount.value)
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

        Log.d("TTT", "From CreateTestViewModel:" +
                "_questionList.value= " + _questionList.value)
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
        Log.d("TTT", "From CreateTestViewModel saveTextResults:" +
                " _textResultList.value = " + _textResultList.value)
    }

    fun saveTest() {
        Log.d("TTT", "From CreateTestViewModel saveTest(): первая строка")
        viewModelScope.launch {
            Log.d("TTT", "From CreateTestViewModel saveTest(): внутри viewModelScope")
            testName.value?.let {
                Log.d("TTT", "From CreateTestViewModel saveTest(): testName.value " + it)
                queCount.value?.let { it2 ->
                    Log.d("TTT", "From CreateTestViewModel saveTest(): queCount.value " + it2)
                    questionList.value?.let { it3 ->
                        Log.d("TTT", "From CreateTestViewModel saveTest(): questionList.value " + it3)
                        textResultList.value?.let { it4 ->
                            Log.d("TTT", "From CreateTestViewModel saveTest(): textResultList.value " + it4)
                            saveTestUseCase.saveTest(it, it2, it3, it4)
                            Log.d("TTT", "From CreateTestViewModel saveTest(): " +
                                    "testName.value: " + it +
                                    " queCount.value: " + it2 +
                                    " questionList.value: " + it3 +
                                    " textResultList.value: " + it4)

                        }
                    }
                }
            }
        }
    }
}