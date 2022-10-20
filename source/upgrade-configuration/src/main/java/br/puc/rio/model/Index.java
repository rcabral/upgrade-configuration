package br.puc.rio.model;

import java.util.List;
import java.util.Optional;

public class Index {
	private final int build;
	private final int step;
	
	public Index(List<Build> builds, BuildInformation lastBuildInformation) {
		int buildIndex = 0;
		int stepIndex = 0;
		Optional<Build> lastBuildApplied = getLastBuildApplied(lastBuildInformation);
						
		if (lastBuildApplied.isPresent() && builds.contains(lastBuildApplied.get())) {
			buildIndex = builds.indexOf(lastBuildApplied.get());
			if (lastBuildInformation.getStatus() == Status.COMPLETE) {
				buildIndex++;
			} else {
				stepIndex = lastBuildInformation.getSteps();
			}
		}
		
		build = buildIndex;
		step = stepIndex;
	}

	private Optional<Build> getLastBuildApplied(BuildInformation lastBuildInformation) {
		if(lastBuildInformation != null) {
			return Optional.of(lastBuildInformation.getBuild());
		}
		return Optional.empty();
	}

	public int getBuild() {
		return build;
	}

	public int getStep() {
		return step;
	}

}
