package br.puc.rio.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.evosuite.shaded.org.mockito.Spy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.BuildInformationTest;
import br.puc.rio.model.BuildTest;
import br.puc.rio.model.UpgradeConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class UpgradeTest {
	@Spy
	private Upgrade upgrade;
	@Mock
	private UpgradeConfiguration upgradeConfiguration;
	@Mock
	private EntityManager entityManager;
	@Mock
	private BuildInformationDao buildInformationDao;
	
	private BuildTest buildCreator;
	
	private BuildInformationTest buildInformationTest;
	
	@Before
	public void setUp() {
		buildCreator = new BuildTest();
		buildInformationTest = new BuildInformationTest();
	}
	
	@Test(expected = Test.None.class)
	public final void testExecuteWhenBuildListIsEmpty() {
		BuildInformation buildInformation = buildInformationTest.getBuildInformation();
		
		Mockito.when(buildInformationDao.getLastBuildInformation()).thenReturn(buildInformation);
		Mockito.when(upgradeConfiguration.getBuilds()).thenReturn(new ArrayList<Build>());
		
		upgrade = new Upgrade(upgradeConfiguration, entityManager, buildInformationDao);
		upgrade.execute();
	}
	
	@Test(expected = Test.None.class)
	public final void testExecuteWhenHasBuilsToAppyButStepIsEmpty() {
		BuildInformation buildInformation = buildInformationTest.getBuildInformation();
		List<Build> builds = buildCreator.getBuilds();
		
		Mockito.when(buildInformationDao.getLastBuildInformation()).thenReturn(buildInformation);
		Mockito.when(buildInformationDao.getBuildInformation(Mockito.any(Build.class))).thenReturn(Optional.empty());
		Mockito.when(upgradeConfiguration.getBuilds()).thenReturn(builds);
				
		upgrade = new Upgrade(upgradeConfiguration, entityManager, buildInformationDao);
		upgrade.execute();
	}
	
	
	
	
	
	

}
