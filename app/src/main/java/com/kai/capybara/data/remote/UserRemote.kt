package com.kai.capybara.data.remote

import com.kai.capybara.domain.model.ResponseErrors
import com.kai.capybara.domain.model.Response
import com.kai.capybara.domain.model.User
import com.kai.capybara.domain.model.api.RegisterUserRequest
import com.kai.capybara.domain.use_case.GetScheduleUseCase

class UserRemote(request: Request): Remote(request) {
    fun register(user: User) =
        processResponse(request.registerUser(RegisterUserRequest(user))).result.token

    fun isValidToken(token: String) =
        processResponse(request.checkToken)

}