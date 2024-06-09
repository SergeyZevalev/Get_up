package com.example.presentation

import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.TimePicker
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

class SettingsPage(rootLayoutId: Int = R.id.settingsFragmentRootLayout, rootView: Class<out View> = LinearLayout::class.java) :
    AbstractPage(rootLayoutId, rootView) {

    private val timePicker = attach(TimePicker::class.java, itemId = R.id.timePicker)

    private val musicName = attach(TextView::class.java, itemId = R.id.musicName)

    private val repeatModeName = attach(TextView::class.java, itemId = R.id.repeatModeName)

    private val vibroModeSwitcher = attach(Switch::class.java, itemId = R.id.vibroModeSwitch)

    private val deleteAfterTriggered =
        attach(Switch::class.java, itemId = R.id.deleteAfterTriggered)

    private val descriptionText = attach(TextView::class.java, itemId = R.id.descriptionText)

    private val confirmButton = attach(ImageButton::class.java, itemId = R.id.confirmButton)

    fun checkHours(hours: String) {
        timePicker.check(matches(allOf(withText(hours), isDisplayed())))
    }

    fun checkMinutes(minutes: String) {
        timePicker.check(matches(allOf(withText(minutes), isDisplayed())))
    }

    fun checkMusic(music: String) {
        musicName.check(matches(withText(music)))
    }

    fun checkRepeatMode(repeat: String) {
        repeatModeName.check(matches(withText(repeat)))
    }

    fun checkerTest(value: Boolean, interaction: ViewInteraction) {
        if (value)
            interaction.check(matches(isChecked()))
        else interaction.check(matches(isNotChecked()))
    }

    fun checkVibroIsOn(vibroIsOn: Boolean) {
        checkerTest(vibroIsOn, vibroModeSwitcher)
    }

    fun checkDeleteAfterTriggered(deleteAfterTriggeredMode: Boolean) {
        checkerTest(deleteAfterTriggeredMode, deleteAfterTriggered)
    }

    fun checkDescription(description: String) {
        descriptionText.check(matches(withText(description.substring(0, 9))))
        descriptionText.perform(click())
        //todo
    }

    fun checkHint(hint: String){
        descriptionText.check(matches(withHint(hint)))
    }

    fun clickConfirm() {
        confirmButton.perform(click())
    }

}