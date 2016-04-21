package ru.nekit.android.clean_architecture.presentation;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import ru.nekit.android.clean_architecture.BaseTest;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.view.activities.RepositoryListActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RepositoryListActivityUnitTest extends BaseTest {

    private RepositoryListActivity activity;

    @Before
    public void setup() {
        ActivityController<RepositoryListActivity> controller = Robolectric.buildActivity(RepositoryListActivity.class).create();
        controller.start();
        controller.resume();
        controller.visible();
        activity = controller.get();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void checkViewModifierIsNotNull() throws Exception {
        assertNotNull(activity.getviewModifier());
    }

    @Test
    public void testActivityTitle() {
        assertEquals("Clean Architecture", activity.getResources().getString(R.string.app_name));
    }

}