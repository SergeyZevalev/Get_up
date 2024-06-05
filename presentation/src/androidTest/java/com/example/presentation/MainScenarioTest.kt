package com.example.presentation

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.runner.RunWith

import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class MainScenarioTest {

    companion object {
        private const val DEFAULT_HOURS = "8"
        private const val DEFAULT_MINUTES = "00"
        private const val DEFAULT_MUSIC = "default"
        private const val DEFAULT_REPEAT = "once"
        private const val DEFAULT_VIBRO_IS_ON = false
        private const val DEFAULT_DELETE_AFTER_TRIGGERED = false
        private const val DEFAULT_DESCRIPTION = ""
    }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun test_check_main_page() {
        val mainPage = MainPage()

        mainPage.checkVisibleNow()
        mainPage.checkListIsEmpty()
    }

    @Test
    fun test_add_one_alarm() {
        val mainPage = MainPage()

        mainPage.addAlarm()
        mainPage.checkNotVisibleNow()

        val settingsPage = SettingsPage()
        settingsPage.checkVisibleNow()

        settingsPage.checkHours(DEFAULT_HOURS)
        settingsPage.checkMinutes(DEFAULT_MINUTES)
        settingsPage.checkMusic(DEFAULT_MUSIC)
        settingsPage.checkRepeatMode(DEFAULT_REPEAT)
        settingsPage.checkVibroIsOn(DEFAULT_VIBRO_IS_ON)
        settingsPage.checkDeleteAfterTriggered(DEFAULT_DELETE_AFTER_TRIGGERED)
        settingsPage.checkDescription(DEFAULT_DESCRIPTION)
        settingsPage.clickConfirm()

        settingsPage.checkNotVisibleNow()
        mainPage.checkVisibleNow()

        mainPage.checkListItemCount(1)
    }

}