package com.example.englishease

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.englishease.presentation.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
//class ExampleInstrumentedTest {
//    @Test
////    fun passwordDoesntMatch() {
//////        onView(withId(R.id.login_edittext))
//////            .perform(ViewActions.typeText("fakeLogin@mail.ru"))
//////        onView(withId(R.id.password_edittext))
//////            .perform(ViewActions.typeText("veryStrongPass"))
//////        Espresso.closeSoftKeyboard()
//////        onView(withId(R.id.authorize_btn))
//////            .perform(ViewActions.click())
//////        onView(withId(R.id.edittexttex))
////
////    }
    class ExampleInstrumentedTest {
//        @Rule
//        @JvmField
//        var activityScenario = ActivityTestRule(MainActivity::class.java)

        @Test
        fun shortPassword_causesError() {
            onView(withId(R.id.already_have_an_acc_btn))
                .perform(ViewActions.click())
            onView(withId(R.id.email_for_reg_edittext))
                .perform(ViewActions.typeText("testperson@mail.ru"))
            onView(withId(R.id.pass_for_reg_edittext))
                .perform(ViewActions.typeText("verystrongpass"))
            onView(withId(R.id.repeat_pass_edittext))
                .perform(ViewActions.typeText("verystrongpass"))
            Espresso.closeSoftKeyboard()
            onView(withId(R.id.register_btn))
                .perform(ViewActions.click())
            onView(withId(R.id.pass_for_reg_edittext))
                .check(ViewAssertions.matches(hasErrorText("Password must contain at least 6 characters")))
        }

        @Test
        fun passwordsDoNotMatch_causesError() {
            onView(withId(R.id.already_have_an_acc_btn))
                .perform(ViewActions.click())
            onView(withId(R.id.email_for_reg_edittext))
                .perform(ViewActions.typeText("testperson@mail.ru"))
            onView(withId(R.id.pass_for_reg_edittext))
                .perform(ViewActions.typeText("verystrongpass"))
            onView(withId(R.id.repeat_pass_edittext))
                .perform(ViewActions.typeText("verystrongpass"))
            Espresso.closeSoftKeyboard()
            onView(withId(R.id.register_btn))
                .perform(ViewActions.click())
            onView(withId(R.id.pass_for_reg_edittext))
                .check(ViewAssertions.matches(hasErrorText("Passwords do not match, check the data you entered")))
        }
    }