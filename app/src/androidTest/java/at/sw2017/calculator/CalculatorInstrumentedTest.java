package at.sw2017.calculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.core.deps.guava.util.concurrent.Service;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 * <p>
 * ​ ​ * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorInstrumentedTest {

    @Rule
    public ActivityTestRule<calculator> mActivityRule = new
            ActivityTestRule<>(calculator.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals(appContext.getPackageName(), appContext.getPackageName());
    }

    @Test
    public void testInvalidThings() throws Exception {
        // Context of the app under test.
        onView(withId(R.id.buttonEqual)).perform(click());
    }


    @Test
    public void testButtons() throws Exception {
        for (int i = 1; i <= 9; i++) {
            onView(withText(Integer.toString(i))).perform(click());
        }
        onView(withId(R.id.button1)).perform(click());

        onView(withText("+")).perform(click());
        onView(withText("-")).perform(click());
        onView(withText("*")).perform(click());
        onView(withText("/")).perform(click());

        onView(withText("=")).perform(click());
        onView(withText("C")).perform(click());
    }

    @Test
    public void testInputField() {
        for(int i = 9; i >= 1; i--){
            onView(withText(Integer.toString(i))).perform(click());
        }
        onView(withText("987654321")).check(matches(isDisplayed()));
    }

    @Test
    public void testPlusField() {

        onView(withText("7")).perform(click());
        onView(withText("+")).perform(click());
        onView(withText("6")).perform(click());
        onView(withText("=")).perform(click());

        onView(withText("13")).check(matches(isDisplayed()));
    }

    @Test
    public void testMinusField() {

        onView(withText("7")).perform(click());
        onView(withText("-")).perform(click());
        onView(withText("6")).perform(click());
        onView(withText("=")).perform(click());

        onView(withId(R.id.textView)).check(matches(withText("1")));
    }

    @Test
    public void testMultField() {

        onView(withText("7")).perform(click());
        onView(withText("*")).perform(click());
        onView(withText("6")).perform(click());
        onView(withText("=")).perform(click());

        onView(withId(R.id.textView)).check(matches(withText("42")));
    }

    @Test
    public void testDivideField() {

        onView(withText("7")).perform(click());
        onView(withText("/")).perform(click());
        onView(withText("0")).perform(click());
        onView(withText("=")).perform(click());

        onView(withId(R.id.textView)).check(matches(withText("0")));
    }

    @Test
    public void testDivideField2() {

        onView(withText("7")).perform(click());
        onView(withText("/")).perform(click());
        onView(withText("6")).perform(click());
        onView(withText("=")).perform(click());

        onView(withId(R.id.textView)).check(matches(withText("1")));
    }

   @Test
    public void testClearButton(){
        onView(withText("3")).perform(click());
        onView(withText("C")).perform(click());

        onView(withId(R.id.textView)).check(matches(withText("0")));
    }
}

