package br.puc.rio.business;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.RunSQLAction;
import br.puc.rio.model.Step;
import br.puc.rio.model.UpgradeConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class DowngradeTest {
	
	private Downgrade downgrade;
	@Mock
	private UpgradeConfiguration upgradeConfiguration;
	@Mock
	private EntityManager entityManager;
	@Mock
	private BuildInformationDao buildInformationDao;
	@Mock
	private List<BuildInformation> appliedBuildsInformation;
	@Mock
	private List<Build> buildsFromXML;
	@Mock
	private BuildInformation lastBuildInformation;
	@Mock
	private BuildInformation buildInformation;
	@Mock
	private Build build;
	@Mock
	private Build lastBuild;
	@Mock
	private Build downgradeBuild;
	@Mock
	private Step step;
	@Mock
	private List<Step> steps;
	@Mock
	private RunSQLAction sqlAction;
	

	
	@Before
	public void setUp() {
		when(buildInformationDao.getAppliedBuilds()).thenReturn(appliedBuildsInformation);
		when(buildInformationDao.getLastBuildInformation()).thenReturn(lastBuildInformation);
		when(lastBuildInformation.getBuild()).thenReturn(lastBuild);
		when(upgradeConfiguration.getDowngradeBuild()).thenReturn(Optional.of(downgradeBuild));
		when(upgradeConfiguration.getBuilds()).thenReturn(buildsFromXML);
		
	}
	
	@Test(expected = Test.None.class)
	public final void testExecuteWhenEmptyBuildsToRevert() {
		downgrade = new Downgrade(upgradeConfiguration, entityManager, buildInformationDao);
		downgrade.execute();
	}
	
	@Test(expected = Test.None.class)
	public final void testExecuteWhenUpToDate() {
		when(upgradeConfiguration.getDowngradeBuild()).thenReturn(Optional.of(lastBuild));
		downgrade = new Downgrade(upgradeConfiguration, entityManager, buildInformationDao);
		downgrade.execute();
	}
	
	@Test(expected = Test.None.class)
	public final void testExecute() {
		when(downgradeBuild.compareTo(any(BuildInformation.class))).thenReturn(-1);
		when(appliedBuildsInformation.contains(any(BuildInformation.class))).thenReturn(true);
		when(appliedBuildsInformation.size()).thenReturn(3);
		when(appliedBuildsInformation.get(anyInt())).thenReturn(buildInformation);
		when(buildsFromXML.contains(any(Build.class))).thenReturn(true);
		when(buildsFromXML.get(anyInt())).thenReturn(build);
		when(build.getSteps()).thenReturn(Arrays.asList(step,step,step));
		when(step.getDowngradeAction()).thenReturn(Optional.of(sqlAction));
		
		downgrade = new Downgrade(upgradeConfiguration, entityManager, buildInformationDao);
		downgrade.execute();
	}
	
	@Test(expected = Test.None.class)
	public final void testExecuteWhenDonwgradeActionIsNotPresent() {
		when(downgradeBuild.compareTo(any(BuildInformation.class))).thenReturn(-1);
		when(appliedBuildsInformation.contains(any(BuildInformation.class))).thenReturn(true);
		when(appliedBuildsInformation.size()).thenReturn(3);
		when(appliedBuildsInformation.get(anyInt())).thenReturn(buildInformation);
		when(buildsFromXML.contains(any(Build.class))).thenReturn(true);
		when(buildsFromXML.get(anyInt())).thenReturn(build);
		when(build.getSteps()).thenReturn(Arrays.asList(step,step,step));
		when(step.getDowngradeAction()).thenReturn(Optional.empty());
		
		downgrade = new Downgrade(upgradeConfiguration, entityManager, buildInformationDao);
		downgrade.execute();
	}
	
	@Test(expected = Test.None.class)
	public final void testExecuteExceptionFlow() {
		when(downgradeBuild.compareTo(any(BuildInformation.class))).thenReturn(-1);
		when(appliedBuildsInformation.contains(any(BuildInformation.class))).thenReturn(true);
		when(appliedBuildsInformation.size()).thenReturn(3);
		when(appliedBuildsInformation.get(anyInt())).thenReturn(buildInformation);
		when(buildsFromXML.contains(any(Build.class))).thenReturn(true);
		when(buildsFromXML.get(anyInt())).thenReturn(build);
		when(build.getSteps()).thenReturn(steps);
		when(steps.size()).thenReturn(3);
		when(steps.get(2)).thenReturn(step);
		when(steps.get(1)).thenReturn(step);
		when(steps.get(0)).thenThrow(new RuntimeException());
		when(step.getDowngradeAction()).thenReturn(Optional.of(sqlAction));
		
		downgrade = new Downgrade(upgradeConfiguration, entityManager, buildInformationDao);
		downgrade.execute();
	}

}
