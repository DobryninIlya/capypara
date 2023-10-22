package com.kai.capybara

import com.kai.capybara.data.remote.RepositoryImpl
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
class RepoUnitTest {

    private lateinit var repo: RepositoryImpl

    @Before
    fun setUp() {
        repo = RepositoryImpl()
        repo.setUid("token")
    }

    @Test
    fun get_week_test() {
        assertEquals(repo.getWeek().week_parity, EVEN)
    }

    @Test
    fun get_group_test() {
        assertEquals(repo.getGroup(4207), Group(group_id = 24683, groupName = 4207))
    }

    @Test
    fun get_schedule_test() {
        val group = repo.getGroup(4207)
        val schedule = repo.getSchedule(group)
        assert(schedule.monday[0].dayDate != "")
    }


    @Test
    fun register_user_test() {
        val token = repo.registerUser(
            User(
                device_tag = "12312",
                groupName = 4207,
            )
        )
        println(token)
    }
}