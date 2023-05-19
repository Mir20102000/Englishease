package com.example.englishease.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.englishease.data.repository.DomainRepositoryImpl
import com.example.englishease.domain.usecases.*

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    private val domainRepository by lazy(LazyThreadSafetyMode.NONE) {
        DomainRepositoryImpl(application)
    }

    private val beginTestUseCase by lazy(LazyThreadSafetyMode.NONE) {
        BeginTestUseCase(domainRepository)
    }

    private val continueTestUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ContinueTestUseCase(domainRepository)
    }

    private val finishTestUseCase by lazy(LazyThreadSafetyMode.NONE) {
        FinishTestUseCase(domainRepository)
    }

    private val getCountQuestionUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetCountQuestionUseCase(domainRepository)
    }

    private val authorizationUseCase by lazy(LazyThreadSafetyMode.NONE) {
        AuthorizationUseCase(domainRepository)
    }

    private val registrationUseCase by lazy(LazyThreadSafetyMode.NONE) {
        RegistrationUseCase(domainRepository)
    }

    private val showTheoryListUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ShowTheoryListUseCase(domainRepository)
    }

    private val showResultsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ShowResultsUseCase(domainRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            authorizationUseCase,
            registrationUseCase,
            showTheoryListUseCase,
            beginTestUseCase,
            continueTestUseCase,
            finishTestUseCase,
            getCountQuestionUseCase,
            showResultsUseCase
        ) as T
    }
}