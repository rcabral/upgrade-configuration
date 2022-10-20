package br.puc.rio.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IndexTest {

	private BuildTest buildCreator;
	private BuildInformationTest buildInformationCreator;

	@Before
	public void setUp() {
		buildCreator = new BuildTest();
		buildInformationCreator = new BuildInformationTest();
	}

	@Test
	public final void testIndexWithCompleteSteps() {
		int buildIndex =  6; 
		int stepIndex = 0;
		List<Build> builds = buildCreator.getBuilds();
		BuildInformation lastBuildInformation = buildInformationCreator.getCompleteBuildInformation();

		Index index = new Index(builds, lastBuildInformation);
		
		assertEquals(buildIndex, index.getBuild());
		assertEquals(stepIndex, index.getStep());
	}
	
	@Test
	public final void testIndexWithParcialSteps() {
		int buildIndex =  5; 
		int stepIndex = 2;
		List<Build> builds = buildCreator.getBuilds();
		BuildInformation lastBuildInformation = buildInformationCreator.getParcialBuildInformation();

		Index index = new Index(builds, lastBuildInformation);
		
		assertEquals(buildIndex, index.getBuild());
		assertEquals(stepIndex, index.getStep());
	}
	
	@Test
	public final void testIndexWithNullLastBuildInformation() {
		int buildIndex =  0; 
		int stepIndex = 0;
		List<Build> builds = buildCreator.getBuilds();
		BuildInformation lastBuildInformation = null;

		Index index = new Index(builds, lastBuildInformation);
		
		assertEquals(buildIndex, index.getBuild());
		assertEquals(stepIndex, index.getStep());
	}
}
