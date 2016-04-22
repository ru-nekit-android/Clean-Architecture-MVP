package ru.nekit.android.clean_architecture.presentation;

import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import java.io.IOException;

import ru.nekit.android.clean_architecture.core.BaseTest;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.view.activities.RepositoryListActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RepositoryListActivityUnitTest extends BaseTest {

    private RepositoryListActivity activity;
    private ActivityController<RepositoryListActivity> controller;

    @Override
    public void setUp() throws IOException {
        super.setUp();
        controller = Robolectric.buildActivity(RepositoryListActivity.class).create();
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