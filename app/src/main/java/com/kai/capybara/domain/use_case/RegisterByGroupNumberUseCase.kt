package com.kai.capybara.domain.use_case

import com.kai.capybara.domain.model.LocalStorage
import com.kai.capybara.domain.model.Repository
import com.kai.capybara.domain.model.User

class RegisterByGroupNameUseCase(
    private val repository: Repository,
    private val localStorage: LocalStorage,
) {

    class WrongGroupNameException : Exception()
    class RegistrationUnavailableException : Exception()

    fun invoke(groupName: Int): User {

        try {
            if (!repository.isValidGroupName(groupName))
                throw WrongGroupNameException()

            val user = repository.registerUser(
                User(groupName = groupName)
            );

            localStorage.saveToken(user.token!!)


            localStorage.saveGroupName(user.groupName)

            return user

        } catch (_: Repository.UnavailableRepositoryException) {
            throw RegistrationUnavailableException()
        }

    }
}