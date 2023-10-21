package com.example.capybara.domain.use_case

import com.example.capybara.domain.model.LocalStorage
import com.example.capybara.domain.model.Repository
import com.example.capybara.domain.model.User

class RegisterBygroupNameUseCase(
    private val repository: Repository,
    private val localStorage: LocalStorage,
) {

    class WronggroupNameException : Exception()
    class RegistrationUnavailableException : Exception()

    fun invoke(groupName: Int): User {

        try {
            val uid: String = repository.registerUser();

            localStorage.saveUid(uid)

            if (!repository.isValidgroupName(groupName))
                throw WronggroupNameException()

            localStorage.savegroupName(uid)

            return User(uid = uid, groupName = groupName)

        } catch (_: Repository.UnavailableRepositoryException) {
            throw RegistrationUnavailableException()
        }

    }
}