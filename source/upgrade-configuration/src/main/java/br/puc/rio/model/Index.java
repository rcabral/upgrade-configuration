package br.puc.rio.model;

import java.util.List;

public class Index {
	private final int build;
	private final int step;
	
	public Index(List<Build> builds, BuildInformation lastBuildInformation) {
		int buildIndex = 0;
		int stepIndex = 0;
		Build lastBuildApplied = lastBuildInformation.getBuild();

		if (lastBuildApplied != null && builds.contains(lastBuildApplied)) {
			buildIndex = builds.indexOf(lastBuildApplied);
			if (lastBuildInformation.getStatus() == Status.COMPLETE) {
				buildIndex++;
			} else {
				stepIndex = lastBuildInformation.getSteps();
			}
		}
		
		build = buildIndex;
		step = stepIndex;
	}

	public int getBuild() {
		return build;
	}

	public int getStep() {
		return step;
	}

}
