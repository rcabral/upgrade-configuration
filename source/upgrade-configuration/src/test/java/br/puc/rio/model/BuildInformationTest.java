package br.puc.rio.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class BuildInformationTest {
	
	private BuildTest buildCreator;
	private int steps = 0;
	private Status status = Status.COMPLETE;
	private BuildInformation buildInformation;

	
	
	@Before
	public void setUp() {
		buildCreator = new BuildTest();
		buildCreator.setUp();
		
		Build build = buildCreator.getBuild();
		buildInformation = new BuildInformation(build,steps,status);
	}

	@Test
	public final void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testBuildInformation() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testBuildInformationBuildIntStatus() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testBuildInformationBuild() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBuild() {
		fail("Not yet implemented"); // TODO
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
	public final void testGetMajorVersion() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetMinorVersion() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetReleaseVersion() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBuildNumber() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBuildSequence() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetSteps() {
		fail("Not yet implemented"); // TODO
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
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCompareToBuildInformation() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCompareToBuild() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEqualsObject() {
		fail("Not yet implemented"); // TODO
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
