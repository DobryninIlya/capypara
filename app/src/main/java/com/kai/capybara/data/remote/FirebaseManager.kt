package com.kai.capybara.data.remote

import com.google.android.gms.tasks.Tasks
import com.kai.capybara.domain.model.RegisterInterface
import com.google.firebase.auth.FirebaseAuth


class FirebaseManager : RegisterInterface {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun getNewUid(): String {
        val task = auth.signInAnonymously()
        Tasks.await(task)
        return task.result.user!!.uid
    }


}