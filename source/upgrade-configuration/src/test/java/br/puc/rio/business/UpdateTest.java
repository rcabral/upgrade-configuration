package br.puc.rio.business;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.BuildInformationTest;
import br.puc.rio.model.UpgradeConfiguration;
import br.puc.rio.model.UpgradeConfigurationTest;

@RunWith(MockitoJUnitRunner.class)
public class UpdateTest {
	
	private UpgradeConfigurationTest upgradeCreator;
	private BuildInformationTest buildInformationCreator;
	@Mock
	private EntityManager entityManager;
	@Mock
	private BuildInformationDao buildInformationDao;
	
	@Before
	public void setUp() {
		upgradeCreator = new UpgradeConfigurationTest();
		upgradeCreator.setUp();
		buildInformationCreator = new BuildInformationTest();
	}

	@Test
	public final void testCreateUpgrade() {
		UpgradeConfiguration upgradeConfiguration = upgradeCreator.getUpgradeConfiguration();
		Update upgrade = Update.create(upgradeConfiguration, entityManager, buildInformationDao);
		
		assertTrue(upgrade instanceof Upgrade);
	}
	
	@Test
	public final void testCreateDowngrade() {
		UpgradeConfiguration downgradeConfiguration = upgradeCreator.getUpgradeDowngradeConfiguration();
		BuildInformation lastBuildInformation = buildInformationCreator.getCompleteBuildInformation();
		
		when(buildInformationDao.getAppliedBuilds()).thenReturn(new ArrayList<>());
		when(buildInformationDao.getLastBuildInformation()).thenReturn(lastBuildInformation);
		Update downgrade = Update.create(downgradeConfiguration, entityManager, buildInformationDao);
		
		assertTrue(downgrade instanceof Downgrade);
	}

}
