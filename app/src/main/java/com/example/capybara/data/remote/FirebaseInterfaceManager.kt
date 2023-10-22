package com.example.capybara.data.remote

import com.example.capybara.domain.model.RegisterInterface
import com.google.firebase.auth.FirebaseAuth


class FirebaseManager : RegisterInterface {

    val auth = FirebaseAuth.getInstance()
    override fun registerUser(): String {
        return auth.signInAnonymously().result.user!!.uid
    }
}