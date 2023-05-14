package com.example.englishease.domain.usecases

import com.example.englishease.domain.models.User
import com.example.englishease.domain.repository.DomainRepository

class AuthorizationUseCase(private val domainRepository: DomainRepository) {
    fun authorization(user: User) {
        domainRepository.authorization(user)
    }
}