package br.puc.rio.business;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.RunSQLAction;
import br.puc.rio.model.Step;
import br.puc.rio.model.UpgradeConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class UpgradeTest {
	private Upgrade upgrade;
	@Mock
	private UpgradeConfiguration upgradeConfiguration;
	@Mock
	private EntityManager entityManager;
	@Mock
	private BuildInformationDao buildInformationDao;
	@Mock
	private BuildInformation buildInformation;
	@Mock
	private Build build;
	@Mock
	private Step step;
	@Mock
	private RunSQLAction sqlAction;
	
	@Before
	public void setUp() {
		when(buildInformationDao.getLastBuildInformation()).thenReturn(buildInformation);
		when(buildInformationDao.getBuildInformation(build)).thenReturn(Optional.of(buildInformation));
		when(buildInformation.getBuild()).thenReturn(build);
		when(build.getSteps()).thenReturn(Arrays.asList(step,step));
		when(upgradeConfiguration.getBuilds()).thenReturn(Arrays.asList(build));
		when(step.getUpgradeAction()).thenReturn(sqlAction);
	}
	
	@Test(expected = Test.None.class)
	public final void testExecuteWhenBuildListIsEmpty() {
		Mockito.when(upgradeConfiguration.getBuilds()).thenReturn(new ArrayList<>());
		upgrade = new Upgrade(upgradeConfiguration, entityManager, buildInformationDao);
		upgrade.execute();
	}
	
	
	@Test(expected = Test.None.class)
	public final void testExecuteWhenLastBuildInformationIsNull() {
		Mockito.when(buildInformationDao.getLastBuildInformation()).thenReturn(null);		
		upgrade = new Upgrade(upgradeConfiguration, entityManager, buildInformationDao);
		upgrade.execute(); 
	}
	
	@Test(expected = Test.None.class)
	public final void testExecuteWhenHasBuilsToAppyButStepIsEmpty() {
		when(buildInformationDao.getBuildInformation(Mockito.any(Build.class))).thenReturn(Optional.empty());
		when(build.getSteps()).thenReturn(new ArrayList<>()); 
		upgrade = new Upgrade(upgradeConfiguration, entityManager, buildInformationDao);
		upgrade.execute(); 
	}
	
	
	@Test(expected = Test.None.class)
	public final void testExecute() {
		upgrade = new Upgrade(upgradeConfiguration, entityManager, buildInformationDao);
		upgrade.execute(); 
	}
	
	
	@Test(expected = Test.None.class)
	public final void testExecuteExceptionFlow() {
		when(buildInformationDao.getBuildInformation(build)).thenThrow(new RuntimeException());
		upgrade = new Upgrade(upgradeConfiguration, entityManager, buildInformationDao);
		upgrade.execute(); 
	}
	
	
	
	
	

}
