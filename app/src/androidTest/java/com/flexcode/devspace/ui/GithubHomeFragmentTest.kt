package com.flexcode.devspace.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.flexcode.devspace.R
import com.flexcode.devspace.core.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GithubHomeFragmentTest {

    /**
     * change home fragment to inital fragment in nav_graph for these tests to pass
     */

    @Rule
    @JvmField
    var scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isFollowersTextViewClickable() {
        Espresso.onView(withId(R.id.textView1))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun isFollowingTextViewClickable() {
        Espresso.onView(withId(R.id.textView2))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun isWebsiteLinkTextViewClickable() {
        Espresso.onView(withId(R.id.tvlink))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun isTwitterLinkTextViewClickable() {
        Espresso.onView(withId(R.id.twitter))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun isSettingsIconClickable() {
        Espresso.onView(withId(R.id.ivSettings))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun isShareIconClickable() {
        Espresso.onView(withId(R.id.ivShare))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

    @Test
    fun isUserProfileDisplayed() {
        Espresso.onView(withId(R.id.ivUserProfile))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
