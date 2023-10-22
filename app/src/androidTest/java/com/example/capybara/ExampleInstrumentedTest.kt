package com.example.capybara

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.capybara.data.local.SharedPreferenceManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.capybara", appContext.packageName)
    }

    fun save_uid_test() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.capybara", appContext.packageName)
        val sharedPreferenceManager = SharedPreferenceManager(appContext)
        val uid = "123"
        sharedPreferenceManager.saveToken(uid)
        assertEquals(uid, sharedPreferenceManager.getUid())

    }
}