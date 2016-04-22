package ru.nekit.android.clean_architecture.data;

import org.junit.Test;

import java.io.IOException;

import javax.inject.Inject;

import ru.nekit.android.clean_architecture.core.BaseTest;
import ru.nekit.android.clean_architecture.core.TestUtils;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;

/**
 * Created by ru.nekit.android on 22.04.16.
 */
public class GithubRepositoryTest extends BaseTest {


    @Inject
    TestUtils testUtils;

    @Override
    public void setUp() throws IOException {
        super.setUp();
        testApplicationComponent.inject(this);
    }

    @Test
    public void testGetRepositoryList() {

        RepositoryEntity[] repositoryEntities = testUtils.readJson("json/repos", RepositoryEntity[].class);

    }
}
