package com.example.englishease.domain.usecases

import com.example.englishease.domain.models.User
import com.example.englishease.domain.repository.DomainRepository

class RegistrationUseCase(private val domainRepository: DomainRepository) {
    fun registration(user: User): Boolean {
        return domainRepository.registration(user)
    }
}