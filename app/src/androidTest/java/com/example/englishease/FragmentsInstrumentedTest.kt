package com.example.englishease

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.englishease.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CompletableFuture.allOf


@RunWith(AndroidJUnit4::class)
class FragmentsInstrumentedTest {

    @Rule
    @JvmField
    var activityScenario = ActivityTestRule(MainActivity::class.java)

//    @Test
//    fun registerExistingUser_causesError() {
//        Espresso.onView(ViewMatchers.withId(R.id.create_acc_btn))
//            .perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.email_for_reg_edittext))
//            .perform(ViewActions.typeText("testperson@mail.ru"))
//        Espresso.onView(ViewMatchers.withId(R.id.pass_for_reg_edittext))
//            .perform(ViewActions.typeText("verystrongpass"))
//        Espresso.onView(ViewMatchers.withId(R.id.repeat_pass_edittext))
//            .perform(ViewActions.typeText("verystrongpass"))
//        Espresso.closeSoftKeyboard()
//        Espresso.onView(ViewMatchers.withId(R.id.register_btn))
//            .perform(ViewActions.click())
//        Espresso.onView(withText("Registration failed, check the data entered"))
//            .check(ViewAssertions.matches(isDisplayed()));
//    }

    @Test
    fun passwordsDoNotMatch_causesError() {
        Espresso.onView(ViewMatchers.withId(R.id.create_acc_btn))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.email_for_reg_edittext))
            .perform(ViewActions.typeText("testperson@mail.ru"))
        Espresso.onView(ViewMatchers.withId(R.id.pass_for_reg_edittext))
            .perform(ViewActions.typeText("verystrong"))
        Espresso.onView(ViewMatchers.withId(R.id.repeat_pass_edittext))
            .perform(ViewActions.typeText("strongpass"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.register_btn))
            .perform(ViewActions.click())
        Espresso.onView(withText("Passwords do not match, check the data you entered"))
            .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    fun shortPassword_causesError() {
        Espresso.onView(withId(R.id.create_acc_btn))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.email_for_reg_edittext))
            .perform(ViewActions.typeText("testperson@mail.ru"))
        Espresso.onView(withId(R.id.pass_for_reg_edittext))
            .perform(ViewActions.typeText("very"))
        Espresso.onView(withId(R.id.repeat_pass_edittext))
            .perform(ViewActions.typeText("very"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.register_btn))
            .perform(ViewActions.click())
        Espresso.onView(withText("Password must contain at least 6 characters"))
            .check(ViewAssertions.matches(isDisplayed()));
    }

//    @Test
//    fun wrongEmailFormat_causesError() {
//        Espresso.onView(ViewMatchers.withId(R.id.create_acc_btn))
//            .perform(ViewActions.click())
//        Espresso.onView(ViewMatchers.withId(R.id.email_for_reg_edittext))
//            .perform(ViewActions.typeText("Random"))
//        Espresso.onView(ViewMatchers.withId(R.id.pass_for_reg_edittext))
//            .perform(ViewActions.typeText("randomrandom"))
//        Espresso.onView(ViewMatchers.withId(R.id.repeat_pass_edittext))
//            .perform(ViewActions.typeText("randomrandom"))
//        Espresso.closeSoftKeyboard()
//        Espresso.onView(ViewMatchers.withId(R.id.register_btn))
//            .perform(ViewActions.click())
//        Espresso.onView(withText("Registration failed, check the data entered"))
//            .check(ViewAssertions.matches(isDisplayed()));
//    }

//    @Test
//    fun wrongEmail_causesError() {
//        Espresso.onView(ViewMatchers.withId(R.id.login_edittext))
//            .perform(ViewActions.typeText("Random"))
//        Espresso.onView(ViewMatchers.withId(R.id.password_edittext))
//            .perform(ViewActions.typeText("1234567"))
//        Espresso.closeSoftKeyboard()
//        Espresso.onView(ViewMatchers.withId(R.id.authorize_btn))
//            .perform(ViewActions.click())
//        Espresso.onView(withText("Login failed, check the data entered"))
//            .check(ViewAssertions.matches(isDisplayed()));
//    }




}