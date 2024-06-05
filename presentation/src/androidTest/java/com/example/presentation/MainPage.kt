package com.example.presentation

import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matchers.allOf


class MainPage(rootLayoutId: Int = R.id.mainFragmentRootLayout) :
    AbstractPage(rootLayoutId) {

    private val recyclerVIewId = R.id.mainFragmentRecycler
    private val addAlarmButtonId = R.id.mainAddAlarmButton
    private val addAlarmButton =
        onView(
            allOf(
                isAssignableFrom(ImageButton::class.java),
                withParent(withId(rootLayoutId)),
                withId(addAlarmButtonId)
            )
        )
    private val recyclerView =
        onView(
            allOf(
                isAssignableFrom(RecyclerView::class.java),
                withParent(withId(rootLayoutId)),
                withId(recyclerVIewId)
            )
        )

    fun checkListIsEmpty() {
        recyclerView.check(RecyclerViewItemCountMatcher(recyclerVIewId, 0))
    }

    fun checkListItemCount(itemCount: Int){
        recyclerView.check(RecyclerViewItemCountMatcher(recyclerVIewId, itemCount))
    }

    fun addAlarm() {
        addAlarmButton.perform(click())
    }
}