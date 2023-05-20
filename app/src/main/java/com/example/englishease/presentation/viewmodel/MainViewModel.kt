package com.example.englishease.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishease.domain.models.Question
import com.example.englishease.domain.models.User
import com.example.englishease.domain.usecases.AuthorizationUseCase
import com.example.englishease.domain.usecases.BeginTestUseCase
import com.example.englishease.domain.usecases.ContinueTestUseCase
import com.example.englishease.domain.usecases.FinishTestUseCase
import com.example.englishease.domain.usecases.GetCountQuestionUseCase
import com.example.englishease.domain.usecases.RegistrationUseCase
import com.example.englishease.domain.usecases.ShowResultsUseCase
import com.example.englishease.domain.usecases.ShowTheoryListUseCase
import kotlinx.coroutines.launch

class MainViewModel (
    private val authorizationUseCase: AuthorizationUseCase,
    private val registrationUseCase: RegistrationUseCase,
    private val showTheoryListUseCase: ShowTheoryListUseCase,
    private val beginTestUseCase: BeginTestUseCase,
    private val continueTestUseCase: ContinueTestUseCase,
    private val finishTestUseCase: FinishTestUseCase,
    private val getCountQuestionUseCase: GetCountQuestionUseCase,
    private val showResultsUseCase: ShowResultsUseCase
    ) : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _theory = MutableLiveData<List<String>>()
    var catalog: LiveData<List<String>> = _theory

    private val _testName = MutableLiveData<String>()
    var testName: LiveData<String> = _testName

    private val _theoryText = MutableLiveData<String>()
    var theoryText: LiveData<String> = _theoryText

    private val _question = MutableLiveData<Question>()
    var question: LiveData<Question> = _question

    private val _declaration = MutableLiveData<String>()
    var declaration: LiveData<String> = _declaration

    private val _queCount = MutableLiveData<Int>()
    var queCount: LiveData<Int> = _queCount

    private val _pointsResult = MutableLiveData<Int>()
    var pointsResult: LiveData<Int> = _pointsResult

    private val _textResult = MutableLiveData<String>()
    var textResult: LiveData<String> = _textResult

    private val _results = MutableLiveData<List<com.example.englishease.domain.models.Conclusion>>()
    var results: LiveData<List<com.example.englishease.domain.models.Conclusion>> = _results

    fun authorize(user: String) {
            _userName.value = user.substringBefore('@')
    }

    fun register(user: User) {
        //_success = registrUseCase.registr(user)
        _success.value = true
        if (_success.value == true) {
            _userName.value = user.name.substringBefore('@')
        }
    }

    fun showTheoryList() {
        viewModelScope.launch {
            _theory.value = showTheoryListUseCase.showTheoryList()
        }
    }

    fun saveTestName(name: String) {
        _testName.value = name
    }

    fun getDeclTest(name: String) {
        viewModelScope.launch {
            _declaration.value = beginTestUseCase.beginTest(name)
        }
    }

    fun setQuestionNumber(num: Int) {
        _question.value?.questionNumber = num
    }

    fun getQuestionCount(name: String) {
        viewModelScope.launch {
            _queCount.value = getCountQuestionUseCase.getCountQue(name)
        }
    }

    fun getQuestion(name: String, num: Int) {
        viewModelScope.launch {
            _question.value = continueTestUseCase.continueTest(name, num)
        }
    }

    fun setPoints(points: Int) {
        _pointsResult.value = points
    }

    fun getResult(nameTest: String, points: Int, nameUser: String) {
        viewModelScope.launch {
            _textResult.value = finishTestUseCase.finishTest(nameTest, points, nameUser)
        }
    }

    fun getResults(nameTest: String) {
        viewModelScope.launch {
            _results.value = showResultsUseCase.showResults(nameTest)
        }
    }
}