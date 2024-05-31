package com.example.englishease.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.englishease.data.repository.DomainRepositoryImpl
import com.example.englishease.domain.usecases.SaveLessonUseCase

class CreateLessonViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    private val domainRepository by lazy(LazyThreadSafetyMode.NONE) {
        DomainRepositoryImpl(application)
    }

    private val saveLessonUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveLessonUseCase(domainRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateLessonViewModel(saveLessonUseCase) as T
    }
}