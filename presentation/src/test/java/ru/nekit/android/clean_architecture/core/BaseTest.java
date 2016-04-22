package ru.nekit.android.clean_architecture.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import java.io.IOException;

import ru.nekit.android.clean_architecture.di.TestApplicationComponent;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
@RunWith(GithubTestApplicationRobolectricUnitTestRunner.class)
@Ignore
public class BaseTest {

    protected TestApplicationComponent testApplicationComponent;

    @Before
    public void setUp() throws IOException {
        testApplicationComponent = GithubTestApplicationRobolectricUnitTestRunner.getTestApplicationComponent();
    }

    @After
    public void tearDown() throws Exception {

    }

}
