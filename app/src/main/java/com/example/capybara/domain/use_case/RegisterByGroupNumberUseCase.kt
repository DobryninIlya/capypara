package com.example.capybara.domain.use_case

import com.example.capybara.data.local.SharedPreferenceManager
import com.example.capybara.data.remote.Repository
import com.example.capybara.domain.model.User

class RegisterByGroupNumberUseCase(
    private val repository: Repository,
    private val localStorage: SharedPreferenceManager,
) {
    fun invoke(groupNumber: Int): User {

        if (!repository.isValidGroupNumber(groupNumber)) {
            throw WrongGroupNumberException()
        }

        val uid: String = repository.getUid();
        localStorage.saveUid(uid)

        return User(uid = uid)
    }
}