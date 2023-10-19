package com.example.capybara

import com.example.capybara.data.remote.RepositoryImpl
import com.example.capybara.domain.model.schedule.EVEN
import com.example.capybara.domain.model.schedule.Group
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ScheduleUnitTest {

    private lateinit var repo: RepositoryImpl
    @Before
    fun setUp() {
        repo = RepositoryImpl()
        repo.setUid("token")
    }
    @Test
    fun get_week_test() {
        assertEquals(repo.getWeek().type, EVEN)
    }
    @Test
    fun get_group_test() {
        assertEquals(repo.getGroup(4207), Group(groupId=24683, groupNumber=4207))
    }

    @Test
    fun get_schedule_test() {
        val group = repo.getGroup(4207)
        val schedule = repo.getSchedule(group)
        assert(schedule.monday[0].dayDate != "")
    }
}