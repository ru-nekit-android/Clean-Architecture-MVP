package ru.nekit.android.clean_architecture.presentation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.view.activities.RepositoryListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ru.nekit.android on 06.05.16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RepositoryListFragmentTest {

    private static final String USER_NAME = "ru-nekit-android";

    @Rule
    public ActivityTestRule<RepositoryListActivity> mActivityRule = new ActivityTestRule(RepositoryListActivity.class);

    @Before
    public void setup() {
    }

    @Test
    public void enterOwner() {
        onView(withId(R.id.user_name_input)).perform(clearText());
        onView(withId(R.id.user_name_input)).perform(typeText(USER_NAME));
        onView(withId(R.id.search_repositories_button)).perform(click());
    }
}
