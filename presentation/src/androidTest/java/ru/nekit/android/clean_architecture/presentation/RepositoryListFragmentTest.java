package ru.nekit.android.clean_architecture.presentation;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentManager;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.view.activities.RepositoryListActivity;
import ru.nekit.android.clean_architecture.presentation.view.fragments.RepositoryListFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.squareup.spoon.Spoon.screenshot;

/**
 * Created by ru.nekit.android on 06.05.16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RepositoryListFragmentTest {

    private static final String USER_NAME = "ru-nekit-android";

    @Rule
    public ActivityTestRule<RepositoryListActivity> mActivityRule = new ActivityTestRule<>(RepositoryListActivity.class, true, false);

    @Before
    public void setup() {
        mActivityRule.launchActivity(new Intent());
    }

    @Test
    public void enterOwner() {
        //setupFragment();
        screenshot(mActivityRule.getActivity(), "empty");
        onView(withId(R.id.user_name_input)).perform(clearText());
        onView(withId(R.id.user_name_input)).perform(typeText(USER_NAME));
        screenshot(mActivityRule.getActivity(), "with_entered_user_name");
        onView(withId(R.id.search_repositories_button)).perform(click());
        onView(withId(R.id.user_name_input)).perform(closeSoftKeyboard());
        screenshot(mActivityRule.getActivity(), "result");
    }

    private void setupFragment() {
        FragmentManager fragmentManager = mActivityRule.getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, RepositoryListFragment.newInstance())
                .commit();

        // Wait for the fragment to be committed
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
    }
}
