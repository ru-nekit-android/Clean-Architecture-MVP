package ru.nekit.android.clean_architecture.domain;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.clean_architecture.core.BaseTest;
import ru.nekit.android.clean_architecture.domain.entities.OwnerEntity;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.presenter.mapper.RepositoryEntityToRepositoryVOMapper;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

@Ignore
public class MapperTest extends BaseTest {

    @Inject
    protected RepositoryEntityToRepositoryVOMapper mapper;

    @Override
    public void setUp() throws IOException {
        super.setUp();
        mapper = new RepositoryEntityToRepositoryVOMapper();
    }

    @Test
    public void mapperTest() {

        OwnerEntity ownerEntity = new OwnerEntity();

        ownerEntity.setLogin("ru-nekit-android");

        RepositoryEntity entity = new RepositoryEntity();
        entity.setName("repo one");
        entity.setOwner(ownerEntity);
        entity.setDescription("bla bla");
        entity.setStargazersCount(1);
        entity.setForks(1);
        entity.setWatchers(1);
        entity.setId(1);
        RepositoryEntity entity2 = new RepositoryEntity();
        entity2.setName("repo two");
        entity2.setOwner(ownerEntity);
        entity2.setDescription("bla bla");
        entity2.setStargazersCount(1);
        entity2.setForks(1);
        entity2.setWatchers(1);
        entity2.setId(2);

        List<RepositoryEntity> entities = new ArrayList<>(Arrays.asList(entity, entity2));

        TestSubscriber<List<RepositoryVO>> testSubscriber = new TestSubscriber<>();

        Observable.just(entities).map(mapper).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        testSubscriber.assertNoErrors();

        List<RepositoryVO> repositories = testSubscriber.getOnNextEvents().get(0);

        assertNotNull(repositories);

        assertEquals(repositories.size(), 2);

        assertNotNull(repositories.get(0));

        assertNotNull(repositories.get(1));
    }

}