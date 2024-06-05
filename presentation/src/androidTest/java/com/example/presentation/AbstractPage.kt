package com.example.presentation

import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.allOf
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

abstract class AbstractPage(@LayoutRes protected val rootId: Int) {

    fun checkVisibleNow(){
        onView(
            allOf(
                isAssignableFrom(LinearLayout::class.java),
                withId(rootId)
            )
        ).check(matches(isDisplayed()))
    }

    fun checkNotVisibleNow(){
        onView(
            allOf(
                isAssignableFrom(LinearLayout::class.java),
                withId(rootId)
            )
        ).check(doesNotExist())
    }

}