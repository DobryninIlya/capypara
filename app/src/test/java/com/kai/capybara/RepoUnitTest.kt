package com.kai.capybara

import androidx.core.content.res.TypedArrayUtils.getText
import com.kai.capybara.data.remote.CapyparaApi
import com.kai.capybara.data.remote.FirebaseManager
import com.kai.capybara.data.remote.RepositoryImpl
import com.kai.capybara.domain.model.RegisterInterface
import com.kai.capybara.domain.model.User
import com.kai.capybara.domain.model.schedule.EVEN
import com.kai.capybara.domain.model.schedule.Group
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

/**
 * kai local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepoUnitTest {

    private lateinit var repo: RepositoryImpl


    @Before
    fun mockFirebase() {

    }


    @Before
    fun setUp() {
        val mock = mock<RegisterInterface> {
            on { getNewUid() } doReturn "agIckYf3nXRwwUnLF77o1xiFdUS2"
        }

        repo = RepositoryImpl(
            api = CapyparaApi.get(),
            firebase = mock
        )
        repo.setToken("token")
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
        println(schedule)
        assert(schedule.monday[0].dayDate != "")
    }


    @Test
    fun register_user_test() {
        val user = repo.registerUser(
            User(
                device_tag = "12312",
                groupName = 4207,
            )
        )
        assert(user.token != null)
    }
}