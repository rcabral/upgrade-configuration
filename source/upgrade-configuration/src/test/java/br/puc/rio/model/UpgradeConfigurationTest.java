package br.puc.rio.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UpgradeConfigurationTest {

	private BuildTest buildTest;
	
	@Before
	public void setUp() {
		buildTest = new BuildTest();
	}

	@Test
	public final void testIsDowngrade() {
		UpgradeConfiguration upgrade = getUpgradeDowngradeConfiguration();
		assertTrue(upgrade.isDowngrade());
	}

	@Test
	public final void testIsNotDowngrade() {
		UpgradeConfiguration upgrade = getUpgradeConfiguration();
		assertFalse(upgrade.isDowngrade());
	}

	@Test
	public final void testGetDowngradeBuild() {
		List<Build> builds = buildTest.getBuilds();
		Build downgradeBuild = buildTest.getDowngradeBuild();
		builds.add(downgradeBuild);
		
		UpgradeConfiguration upgrade = new UpgradeConfiguration(builds);
		assertEquals(downgradeBuild, upgrade.getDowngradeBuild().get());
	}
	
	@Test
	public final void testGetBuilds() {
		List<Build> builds = buildTest.getBuilds();
		UpgradeConfiguration upgrade = new UpgradeConfiguration(builds);
		assertEquals(builds,upgrade.getBuilds());
	}
	
	public UpgradeConfiguration getUpgradeConfiguration() {
		List<Build> builds = buildTest.getBuilds();
		return new UpgradeConfiguration(builds);
	}
	
	public UpgradeConfiguration getUpgradeDowngradeConfiguration() {
		List<Build> builds = buildTest.getBuildsWithOneDowngrade();
 		return new UpgradeConfiguration(builds);
	}

}
