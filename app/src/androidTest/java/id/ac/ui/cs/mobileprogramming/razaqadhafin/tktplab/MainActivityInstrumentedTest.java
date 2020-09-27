package id.ac.ui.cs.mobileprogramming.razaqadhafin.tktplab;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test for MainActivity class.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void checkPalindrome_givenTruePalindrome_onButtonClick() {
        onView(withId(R.id.textInputPalindrome))
                .perform(replaceText("abc1$$1cba"), closeSoftKeyboard());

        onView(withId(R.id.buttonCheck)).perform(click());

        onView(withId(R.id.labelResult)).check(
                matches(withText(MainActivity.IS_PALINDROME))
        );
    }

    @Test
    public void checkPalindrome_givenFalsePalindrome_onButtonClick() {
        onView(withId(R.id.textInputPalindrome))
                .perform(replaceText("asdae1212ss"), closeSoftKeyboard());

        onView(withId(R.id.buttonCheck)).perform(click());

        onView(withId(R.id.labelResult)).check(
                matches(withText(MainActivity.NOT_PALINDROME))
        );
    }

    @Test
    public void checkPalindrome_givenEmptyInput_onButtonClick() {
        onView(withId(R.id.textInputPalindrome))
                .perform(replaceText(""), closeSoftKeyboard());

        onView(withId(R.id.buttonCheck)).perform(click());

        onView(withId(R.id.labelResult)).check(
                matches(withText(MainActivity.IS_PALINDROME))
        );
    }
}