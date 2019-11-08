package com.example.pizzaProject;
import androidx.test.espresso.ViewAssertion;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class AppTest {
    @Rule public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickItem_FragmentBind() {
        onView(withId(R.id.recycler)).perform(click());

        onView(withId(R.id.specificName));
    }

    @Test
    public void addItem() {
        onView(withId(R.id.floatingActionButton)).perform(click());

        onView(withId(R.id.NameEdit)).perform(typeText("test Pizza"));
        onView(withId(R.id.DescEdit)).perform(typeText("espresso"));
        onView(withId(R.id.PriceEdit)).perform(typeText("2.5"), closeSoftKeyboard());

        onView(withId(R.id.button)).perform(click());

        onView(withText("test Pizza"));
    }
}
