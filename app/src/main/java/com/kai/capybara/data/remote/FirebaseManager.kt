package com.kai.capybara.data.remote

import com.kai.capybara.domain.model.RegisterInterface
import com.google.firebase.auth.FirebaseAuth
import com.kai.capybara.domain.util.await


class FirebaseManager : RegisterInterface {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun getNewUid(): String {
        return auth.signInAnonymously().await().result.user!!.uid
    }


}