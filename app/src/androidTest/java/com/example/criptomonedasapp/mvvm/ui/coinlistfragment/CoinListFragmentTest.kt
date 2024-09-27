package com.example.criptomonedasapp.mvvm.ui.coinlistfragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.criptomonedasapp.HiltFunctions
import org.junit.Test
import org.junit.Rule
import com.example.criptomonedasapp.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before


@HiltAndroidTest
class CoinListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun sepUp(){
        hiltRule.inject()
    }


    @Test
    fun clickOnItem_opensNextUI() {
        HiltFunctions.launchFragmentInHiltContainer<CoinListFragment>()
        //onView(withId(R.id.coinListRecyclerView)).check(matches(isDisplayed()))
       onView(withId(R.id.coinListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
                onView(withId(R.id.coinListFragment)).check(matches(isDisplayed()))

    }
}
