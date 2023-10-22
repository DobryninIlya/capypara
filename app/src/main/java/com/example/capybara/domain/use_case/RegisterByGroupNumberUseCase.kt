package com.example.capybara.domain.use_case

import com.example.capybara.domain.model.LocalStorage
import com.example.capybara.domain.model.Repository
import com.example.capybara.domain.model.User

class RegisterByGroupNameUseCase(
    private val repository: Repository,
    private val localStorage: LocalStorage,
) {

    class WrongGroupNameException : Exception()
    class RegistrationUnavailableException : Exception()

    fun invoke(groupName: Int): User {

        try {

            val user = repository.registerUser(
                User(groupName = groupName)
            );

            localStorage.saveToken(user.token!!)

            if (!repository.isValidgroupName(groupName))
                throw WrongGroupNameException()

            localStorage.saveGroupName(user.groupName)

            return user

        } catch (_: Repository.UnavailableRepositoryException) {
            throw RegistrationUnavailableException()
        }

    }
}