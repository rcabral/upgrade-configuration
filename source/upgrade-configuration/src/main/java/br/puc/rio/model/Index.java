package br.puc.rio.model;

import java.util.List;
import java.util.Optional;

/**
 * 
 * Implements Index of upgrade execution. 
 * Index is a starting point for the upgrade. This starting point always starts after the last step of the last build applied to the server.
 *
 */
public class Index {
	private final int build;
	private final int step;
	
	/**
	 * Constructor Method
	 * @param builds
	 * @param lastBuildInformation
	 */
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
	
	/**
	 * Get number of build
	 * @return int
	 */
	public int getBuild() {
		return build;
	}
	
	/**
	 * Get number of step
	 * @return int
	 */
	public int getStep() {
		return step;
	}

}
