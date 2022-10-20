package br.puc.rio.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BuildTest {
	
	private int majorVersion;
	private int minorVersion;
	private int releaseVersion;
	private String buildNumber;
	private int buildSequence;
	private List<Step> steps;
	private String message;
	private Boolean downgrade;
	private Build build;
	
	@Before
	public void setUp() {
		minorVersion = 1;
		releaseVersion = 5;
		buildNumber = "010122";
		buildSequence = 0;
		steps = StepTest.getSteps();
		message = "Hello Build!";
		downgrade = false;
		build = new Build(majorVersion , minorVersion, releaseVersion,  buildNumber, buildSequence,steps,message,downgrade);
	}

	@Test
	public final void testHashCode() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testBuildIntIntIntStringIntListOfStepString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testBuildIntIntIntStringInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testBuildIntIntIntStringIntBoolean() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCompareToBuild() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCompareToBuildInformation() {
		BuildInformation buildInformation = new BuildInformation(build);
		int EQUAL = 0;
				
		assertEquals(EQUAL, build.compareTo(buildInformation));
	}

	@Test
	public final void testEqualsObject() {
		Build otherBuild = new Build(majorVersion, minorVersion, releaseVersion, buildNumber, buildSequence);
		assertEquals(build, otherBuild);
	}

	@Test
	public final void testGetSteps() {
		assertEquals(steps, build.getSteps());
	}

	@Test
	public final void testGetMajorVersion() {
		assertEquals(majorVersion, build.getMajorVersion());
	}

	@Test
	public final void testGetMinorVersion() {
		assertEquals(minorVersion, build.getMinorVersion());
	}

	@Test
	public final void testGetReleaseVersion() {
		assertEquals(releaseVersion, build.getReleaseVersion());
	}

	@Test
	public final void testGetBuildNumber() {
		assertEquals(buildNumber, build.getBuildNumber());
	}

	@Test
	public final void testGetBuildSequence() {
		assertEquals(buildSequence, build.getBuildSequence());
	}

	@Test
	public final void testGetMessage() {
		assertEquals(message, build.getMessage());
	}
	
	@Test
	public final void testGetMessageWhenMessageIsNull() {
		String message = null;
		List<Step> steps = new ArrayList<>();
		Build build =  new Build(11, 0, 0, "010122", 0,steps,message);

		assertEquals(build.toString(), build.getMessage());
	}

	@Test
	public final void testIsDowngrade() {
		downgrade = true;
		build = new Build(majorVersion , minorVersion, releaseVersion,  buildNumber, buildSequence,steps,message,downgrade);
		assertTrue(build.isDowngrade());
	}
	
	public List<Build> getBuilds(){
		List<Build> builds = new ArrayList<>();
		builds.add(new Build(1, 0, 0, "010122", 0));
		builds.add(new Build(2, 0, 0, "010122", 0));
		builds.add(new Build(3, 0, 0, "010122", 0));
		builds.add(new Build(4, 0, 0, "010122", 0));
		builds.add(new Build(5, 0, 0, "010122", 0));
		builds.add(new Build(6, 0, 0, "010122", 0));
		builds.add(new Build(7, 0, 0, "010122", 0));
		builds.add(new Build(8, 0, 0, "010122", 0));
		builds.add(new Build(9, 0, 0, "010122", 0));
		builds.add(new Build(10, 0, 0, "010122", 0));
		return builds;
	}
	
	public Build getLastBuildInformation(){
		return new Build(6, 0, 0, "010122", 0);
	}
	
	public Build getBuild() {
		return build;
	}

	public List<Build> getBuildsWithOneDowngrade(){
		List<Build> builds = new ArrayList<>();
		builds.add(new Build(1, 0, 0, "010122", 0));
		builds.add(new Build(2, 0, 0, "010122", 0));
		builds.add(new Build(3, 0, 0, "010122", 0));
		builds.add(new Build(4, 0, 0, "010122", 0));
		builds.add(new Build(5, 0, 0, "010122", 0));
		builds.add(new Build(6, 0, 0, "010122", 0));
		builds.add(new Build(7, 0, 0, "010122", 0));
		builds.add(new Build(8, 0, 0, "010122", 0));
		builds.add(new Build(9, 0, 0, "010122", 0, true));
		builds.add(new Build(10, 0, 0, "010122", 0));
		builds.add(new Build(11, 0, 0, "010122", 0));
		return builds;
	}
	
	public Build getDowngradeBuild() {
		return new Build(11, 0, 0, "010122", 0, true);
	}

}
