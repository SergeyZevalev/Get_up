package com.example.presentation

import android.view.View
import androidx.annotation.LayoutRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers.allOf
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

abstract class AbstractPage(@LayoutRes protected val rootId: Int, private val rootView: Class<out View>) {

    fun checkVisibleNow(){
        onView(
            allOf(
                isAssignableFrom(rootView),
                withId(rootId)
            )
        ).check(matches(isDisplayed()))
    }

    fun checkNotVisibleNow(){
        onView(
            allOf(
                isAssignableFrom(rootView),
                withId(rootId)
            )
        ).check(doesNotExist())
    }

    fun attach(view: Class<out View>, rootLayout: Int = rootId, itemId: Int) =
        onView(
            allOf(
                isAssignableFrom(view),
                ViewMatchers.withParent(withId(rootLayout)),
                withId(itemId)
            )
        )


}