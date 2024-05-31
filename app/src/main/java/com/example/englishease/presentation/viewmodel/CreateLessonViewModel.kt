package com.example.englishease.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishease.domain.usecases.SaveLessonUseCase
import kotlinx.coroutines.launch

class CreateLessonViewModel(private val saveLessonUseCase: SaveLessonUseCase) : ViewModel() {
    private val _lessonName = MutableLiveData<String>()
    val lessonName: LiveData<String> = _lessonName

    private val _lessonDesc = MutableLiveData<String>()
    val lessonDesc: LiveData<String> = _lessonDesc

    private val _lessonContent = MutableLiveData<String>()
    val lessonContent: LiveData<String> = _lessonContent

//    fun saveLesson(lessonName: String, lessonDesc: String, lessonContent: String) {
//        _lessonName.value = lessonName
//        _lessonDesc.value = lessonDesc
//        _lessonContent.value = lessonContent
//    }

    fun saveLessonUseCase(lessonName: String, lessonDesc: String, lessonContent: String) {
        viewModelScope.launch {
//                        _lessonName.value = lessonName
//                        _lessonDesc.value = lessonDesc
//                        _lessonContent.value = lessonContent
            saveLessonUseCase.saveLesson(lessonName, lessonDesc, lessonContent)
        }
    }
}
