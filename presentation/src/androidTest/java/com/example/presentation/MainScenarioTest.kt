package com.example.presentation

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
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
        private const val DEFAULT_HINT = "Text..."
        private const val DEFAULT_DESCRIPTION = ""
    }

    private val mainPage = MainPage()
    private val settingsPage = SettingsPage()

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun test_check_main_page() {

        mainPage.checkVisibleNow()
        mainPage.checkListItemCount(0)
    }

    @Test
    fun test_add_one_alarm() {

        mainPage.addAlarm()
        mainPage.checkNotVisibleNow()

        settingsPage.checkVisibleNow()

        settingsPage.checkHours(DEFAULT_HOURS)
        settingsPage.checkMinutes(DEFAULT_MINUTES)
        settingsPage.checkMusic(DEFAULT_MUSIC)
        settingsPage.checkRepeatMode(DEFAULT_REPEAT)
        settingsPage.checkVibroIsOn(DEFAULT_VIBRO_IS_ON)
        settingsPage.checkDeleteAfterTriggered(DEFAULT_DELETE_AFTER_TRIGGERED)
        settingsPage.checkHint(DEFAULT_HINT)
        settingsPage.checkDescription(DEFAULT_DESCRIPTION)
        settingsPage.clickConfirm()

        settingsPage.checkNotVisibleNow()
        mainPage.checkVisibleNow()

        mainPage.checkListItemCount(1)
        mainPage.checkHours(0, DEFAULT_HOURS)
        mainPage.checkMinutes(0, DEFAULT_MINUTES)
        mainPage.checkAlarmIsOn(0, true)
        mainPage.checkVibroIsOn(0, false)
        mainPage.checkDays(0, listOf(0, 1, 2, 3, 4))
    }

    @Test
    fun change_one_alarm() {

        mainPage.checkVisibleNow()
    }

}