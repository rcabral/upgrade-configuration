package br.puc.rio.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;

@RunWith(MockitoJUnitRunner.class)
public class BuildInformationDaoTest {
	@InjectMocks
	private BuildInformationDao buildInformationDao;
	@Mock
	private EntityManager entityManager; 
	@Mock
	private BuildInformation buildInformation;
	@Mock
	private Build build;
	@Mock
	private EntityTransaction transaction;
	@Mock
	private TypedQuery<Object> query;
	@Mock
	private List<Object> builds;
	
	
	@Before
	public void setUp() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		when(entityManager.createQuery(any(),any())).thenReturn(query);
		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.setMaxResults(anyInt())).thenReturn(query);
		when(buildInformation.getBuild()).thenReturn(build);
	}

	@Test(expected = Test.None.class)
	public final void testPersist() {
		buildInformationDao.persist(buildInformation);
	}

	@Test(expected = Test.None.class)
	public final void testMerge() {
		buildInformationDao.merge(buildInformation);
	}

	@Test(expected = Test.None.class)
	public final void testDelete() {
		buildInformationDao.delete(buildInformation);
	}

	@Test
	public final void testGetLastBuildInformation() {
		when(query.getSingleResult()).thenReturn(buildInformation);
		assertEquals(buildInformation, buildInformationDao.getLastBuildInformation());
	}
	
	@Test
	public final void testGetLastBuildInformationWithNoResult() {
		when(query.getSingleResult()).thenThrow(new NoResultException());
		assertNull(buildInformationDao.getLastBuildInformation());
	}

	@Test
	public final void testGetBuildInformationBuild() {
		when(query.getSingleResult()).thenReturn(buildInformation);
		assertEquals(Optional.of(buildInformation), buildInformationDao.getBuildInformation(build));
	}

	@Test
	public final void testGetBuildInformationBuildInformation() {
		when(query.getSingleResult()).thenReturn(buildInformation);
		assertEquals(Optional.of(buildInformation), buildInformationDao.getBuildInformation(buildInformation));
	}
	
	@Test
	public final void testGetBuildInformationBuildInformationWithNoResult() {
		when(query.getSingleResult()).thenThrow(new NoResultException());
		assertEquals(Optional.empty(),buildInformationDao.getBuildInformation(buildInformation));
	}
	
	@Test
	public final void testGetAppliedBuilds() {
		when(query.getResultList()).thenReturn(builds);
		assertEquals(builds, buildInformationDao.getAppliedBuilds());
	}

	@Test
	public final void testGetLastBuild() {
		when(query.getSingleResult()).thenReturn(buildInformation);
		assertEquals(build, buildInformationDao.getLastBuild());
	}
	
	@Test
	public final void testGetLastBuildWithNoResult() {
		when(query.getSingleResult()).thenThrow(new NoResultException());
		assertNull(buildInformationDao.getLastBuild());
	}


	@Test(expected = Test.None.class)
	public final void testUpdateRemainingSteps() {
		int steps = 3;
		buildInformationDao.updateRemainingSteps(buildInformation, steps);
	}

}
