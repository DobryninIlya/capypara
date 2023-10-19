package com.example.capybara

import com.example.capybara.data.remote.RepositoryImpl
import com.example.capybara.domain.model.schedule.WeekType
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun get_week_test() {
        val repo = RepositoryImpl();
        assertEquals(repo.getWeek().type, WeekType.Even)
    }
    @Test
    fun get_schedule() {
        val repo = RepositoryImpl();
        repo.setUid("token")

        println(repo.getGroupId(4207))
    }
}