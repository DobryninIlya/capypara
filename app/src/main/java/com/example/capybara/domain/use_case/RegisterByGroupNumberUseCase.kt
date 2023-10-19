package com.example.capybara.domain.use_case

import com.example.capybara.domain.model.LocalStorage
import com.example.capybara.domain.model.Repository
import com.example.capybara.domain.model.User

class RegisterByGroupNumberUseCase(
    private val repository: Repository,
    private val localStorage: LocalStorage,
) {

    class WrongGroupNumberException : Exception()
    class RegistrationUnavailableException : Exception()

    fun invoke(groupNumber: Int): User {

        try {
            val uid: String = repository.registerUser();

            localStorage.saveUid(uid)

            if (!repository.isValidGroupNumber(groupNumber))
                throw WrongGroupNumberException()

            localStorage.saveGroupNumber(uid)

            return User(uid = uid, groupNumber = groupNumber)

        } catch (_: Repository.UnavailableRepositoryException) {
            throw RegistrationUnavailableException()
        }

    }
}