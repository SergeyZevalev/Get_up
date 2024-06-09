package com.example.presentation

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withText

class MainPage(rootLayoutId: Int = R.id.mainFragmentRootLayout, rootView: Class<out View> = FrameLayout::class.java) :
    AbstractPage(rootLayoutId, rootView) {

    private val recyclerVIewId = R.id.mainFragmentRecycler
    private val addAlarmButtonId = R.id.mainAddAlarmButton
    private val addAlarmButton = attach(ImageButton::class.java, rootLayoutId, addAlarmButtonId)
    private val recyclerView = attach(RecyclerView::class.java, rootLayoutId, recyclerVIewId)

    fun checkListItemCount(itemCount: Int) {
        recyclerView.check(RecyclerViewItemCountMatcher(recyclerVIewId, itemCount))
    }

    fun addAlarm() {
        addAlarmButton.perform(click())
    }

    fun hoursAndMinutesChecker(position: Int, value: String, viewId: Int) {
        onView(RecyclerViewMatcher(recyclerVIewId).atPosition(position, viewId)).check(
            matches(
                withText(value)
            )
        )
    }

    fun recyclerBooleanValueCheck(position: Int, value: Boolean, itemId: Int) {
        val item = onView(RecyclerViewMatcher(recyclerVIewId).atPosition(position, itemId))
        if (value)
            item.check(
                matches(
                    isChecked()
                )
            )
        else item.check(
            matches(
                isNotChecked()
            )
        )
    }

    fun checkHours(position: Int, hours: String) {
        hoursAndMinutesChecker(position, hours, R.id.itemHours)
    }

    fun checkMinutes(position: Int, minutes: String) {
        hoursAndMinutesChecker(position, minutes, R.id.itemMinutes)
    }

    fun checkAlarmIsOn(position: Int, value: Boolean) {
        recyclerBooleanValueCheck(position, value, R.id.itemAlarmIsOn)
    }

    fun checkVibroIsOn(position: Int, value: Boolean) {
        recyclerBooleanValueCheck(position, value, R.id.itemVibroIsOn)
    }

    fun checkDays(position: Int, list: List<Int>) {
        val fullList = listOf(0, 1, 2, 3, 4, 5, 6)
        val item = RecyclerViewMatcher(recyclerVIewId)
        val selectedColor = R.color.forSelected
        val unselectedColor = R.color.mainForIcons
        list.customCheck(item, position, selectedColor)
        fullList.minus(list.toSet()).customCheck(item, position, unselectedColor)
    }

    fun weeksMatcher(matcher: RecyclerViewMatcher, position: Int, itemId: Int, color: Int) {
        matcher.atPosition(position, itemId).matches(hasTextColor(color))
    }

    fun List<Int>.customCheck(item: RecyclerViewMatcher, position: Int, color: Int) {
        this.forEach {
            when (it) {
                0 -> weeksMatcher(item, position, R.id.monday, color)
                1 -> weeksMatcher(item, position, R.id.tuesday, color)
                2 -> weeksMatcher(item, position, R.id.wednesday, color)
                3 -> weeksMatcher(item, position, R.id.thursday, color)
                4 -> weeksMatcher(item, position, R.id.friday, color)
                5 -> weeksMatcher(item, position, R.id.saturday, color)
                else -> weeksMatcher(item, position, R.id.sunday, color)
            }
        }
    }
}