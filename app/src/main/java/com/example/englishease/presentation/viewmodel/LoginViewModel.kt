package com.example.englishease.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _superRole = MutableLiveData<Boolean>()
    val superRole: LiveData<Boolean> = _superRole

    fun login(user: String) {
        _userName.value = user.substringBefore('@')
    }

    fun password(password: String) {
        _password.value = password
    }

    fun setRole(superRole: Boolean) {
        _superRole.value = superRole
    }
}