package br.puc.rio.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class BuildInformationTest {
	
	private BuildTest buildCreator;
	private int steps = 0;
	private Status status = Status.COMPLETE;
	private Build build;
	private Build otherBuild;
	private BuildInformation buildInformation;
	private BuildInformation otherBuildInformation;

	
	
	@Before
	public void setUp() {
		buildCreator = new BuildTest();
		buildCreator.setUp();
		
		build = buildCreator.getBuild();
		otherBuild = buildCreator.getBuild();
		
		buildInformation = new BuildInformation(build,steps,status);
		otherBuildInformation = new BuildInformation(otherBuild,steps,status);
	}

	@Test
	public final void testHashCode() {
		assertEquals(buildInformation.hashCode(), otherBuildInformation.hashCode());
	}

	@Test
	public final void testGetId() {
		String id = buildInformation.getId();
		assertTrue(validadeUUID(id));

	}

	private boolean validadeUUID(String id) {
		return Pattern.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", id);
	}

	@Test
	public final void testGetUpgradeDate() {
		LocalDateTime upgradeDate = buildInformation.getUpgradeDate();
		LocalDateTime now = LocalDateTime.now();
		
		assertEquals(now.getDayOfMonth(), upgradeDate.getDayOfMonth());
		assertEquals(now.getMonth(), upgradeDate.getMonth());
		assertEquals(now.getYear(), upgradeDate.getYear());
	}

	@Test
	public final void testGetStatus() {
		assertEquals(Status.COMPLETE, buildInformation.getStatus());
	}

	@Test
	public final void testToString() {
		assertTrue(buildInformation.toString() instanceof String);
	}

	@Test
	public final void testCompareToBuildInformation() {
		assertEquals(0,buildInformation.compareTo(otherBuildInformation));
	}

	@Test
	public final void testCompareToBuild() {
		assertEquals(0,buildInformation.compareTo(build));
	}

	@Test
	public final void testEqualsObject() {
		assertTrue(buildInformation.equals(otherBuildInformation));
	}
	
	public BuildInformation getBuildInformation(){
		return new BuildInformation();
	}
	
	public BuildInformation getCompleteBuildInformation(){
		Build build = new Build(6, 0, 0, "010122", 0);
		int steps = 5; 
		return new BuildInformation(build,steps, Status.COMPLETE);
	}
	
	public BuildInformation getParcialBuildInformation(){
		Build build = new Build(6, 0, 0, "010122", 0);
		int steps = 2; 
		return new BuildInformation(build,steps, Status.PARCIAL);
	}

}
