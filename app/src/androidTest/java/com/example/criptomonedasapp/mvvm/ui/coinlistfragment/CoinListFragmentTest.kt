package com.example.criptomonedasapp.mvvm.ui.coinlistfragment

import android.support.test.rule.ActivityTestRule
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.criptomonedasapp.MainActivity
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.jvm.Throws
import com.example.criptomonedasapp.R


@RunWith(AndroidJUnit4::class)
class CoinListFragmentTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)


    @Test
    @Throws(Exception::class)
    fun clickStartButton_opensNextUI() {
       onView(withId(R.id.coinListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
                onView(withId(R.id.coinListFragment)).check(matches(isDisplayed()))

    }
}
