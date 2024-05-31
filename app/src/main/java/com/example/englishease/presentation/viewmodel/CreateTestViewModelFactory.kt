package com.example.englishease.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.englishease.data.repository.DomainRepositoryImpl
import com.example.englishease.domain.usecases.SaveTestUseCase

class CreateTestViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    private val testRepository by lazy(LazyThreadSafetyMode.NONE) {
        DomainRepositoryImpl(application)
    }

    private val saveTestUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveTestUseCase(testRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateTestViewModel(saveTestUseCase) as T
    }
}