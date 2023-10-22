package com.kai.capybara

import com.kai.capybara.data.remote.FirebaseManager
import com.google.firebase.auth.FirebaseAuth
import org.junit.Test

import org.junit.Before

/**
 * kai local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FirebaseUnitTest {

    private lateinit var firebase: FirebaseManager

    @Before
    fun setUp() {
        firebase = FirebaseManager()
        firebase.auth = FirebaseAuth.getInstance()
    }

    @Test
    fun register_user_test() {
        val token = firebase.getNewUid()

        println(token)
    }
}