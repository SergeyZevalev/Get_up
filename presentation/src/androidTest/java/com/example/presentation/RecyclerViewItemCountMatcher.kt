package com.example.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.junit.Assert.assertEquals

class RecyclerViewItemCountMatcher(private val recyclerViewId: Int, private val itemCount: Int) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
        val adapter = recyclerView.adapter
        assertEquals(adapter?.itemCount ?: 0 , itemCount)
    }
}