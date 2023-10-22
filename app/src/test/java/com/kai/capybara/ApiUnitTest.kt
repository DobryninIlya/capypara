package com.kai.capybara

import com.kai.capybara.data.remote.CapyparaApi
import com.kai.capybara.domain.model.User


import com.kai.capybara.domain.model.schedule.EVEN
import com.kai.capybara.domain.model.schedule.Group
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * kai local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiUnitTest {

    private lateinit var api: com.kai.capybara.data.remote.CapyparaApi
    private val uid: String = "12421343452646245yhdsfkjbhqih535"

    @Before
    fun setUp() {
        api = CapyparaApi.get()
    }

    @Test
    fun register_user_test() {


    }
}