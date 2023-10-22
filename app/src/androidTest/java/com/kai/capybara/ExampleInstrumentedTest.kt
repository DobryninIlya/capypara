package com.kai.capybara

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kai.capybara.data.remote.FirebaseManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class kaiInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kai.capybara", appContext.packageName)
    }

    fun save_uid_test() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kai.capybara", appContext.packageName)
        val sharedPreferenceManager =
            com.kai.capybara.data.local.SharedPreferenceManager(appContext)
        val uid = "123"
        sharedPreferenceManager.saveToken(uid)
        assertEquals(uid, sharedPreferenceManager.getUid())

    }

    @Test
    fun firebase_register_test() {
        val firebase = FirebaseManager()
        assert(firebase.getNewUid() != "")
    }
}