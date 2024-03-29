package br.puc.rio.business;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.Index;
import br.puc.rio.model.Status;
import br.puc.rio.model.Step;
import br.puc.rio.model.UpgradeConfiguration;

/**
 * 
 * Implements Update, to create a upgrade process.
 *
 */
public class Upgrade implements Update {
	private EntityManager entityManager;
	private BuildInformationDao buildInformationDao;
	private BuildInformation lastBuildInformation;
	private Build lastAppliedBuild;
	private List<Build> builds;
	private Index index;
	private static final Logger logger = LogManager.getLogger(Upgrade.class);
	
	/**
	 * Constructor Method.
	 * @param upgradeConfiguration
	 * @param entityManager
	 * @param buildInformationDao
	 */
	public Upgrade(UpgradeConfiguration upgradeConfiguration, EntityManager entityManager, BuildInformationDao buildInformationDao) {
		this.entityManager = entityManager;
		this.buildInformationDao = buildInformationDao; 
		this.lastBuildInformation = buildInformationDao.getLastBuildInformation();
		this.lastAppliedBuild = getLastAppliedBuild(lastBuildInformation);
		this.builds = upgradeConfiguration.getBuilds();
		this.index = new Index(this.builds, this.lastBuildInformation);
	}

	private Build getLastAppliedBuild(BuildInformation lastBuildInformation) {
		Build lastAppliedBuild = null;
		if (lastBuildInformation != null) {
			lastAppliedBuild = lastBuildInformation.getBuild();
		}
		return lastAppliedBuild;
	}
	
	/**
	 * Method used to execute the Upgrade process.
	 */
	public void execute() {
		if (!needToProcess()) {
			return;
		}

		int buildIndex = index.getBuild();
		int stepIndex = index.getStep();

		for (int i = buildIndex; i < builds.size(); i++) {
			Build build = builds.get(i);
			Step lastStepExecuted = null;
			int countSteps = 0;
			try {
				for (int j = stepIndex; build.getSteps() != null && j < build.getSteps().size(); j++) {
					Step step = build.getSteps().get(j);
					lastStepExecuted = step;
					step.getUpgradeAction().execute(entityManager);
					countSteps = j + 1;
				}
				Optional<BuildInformation> appiedBuild = buildInformationDao.getBuildInformation(build);
				if(appiedBuild.isPresent())
					buildInformationDao.delete(appiedBuild.get());
				buildInformationDao.persist(new BuildInformation(build, build.getSteps().size(), Status.COMPLETE));
				logger.info("Upgrade Message: " + build.getMessage());
			} catch (Exception e) {
				logger.error("Upgrade Build execution error:" + build + " " + "Step:" + lastStepExecuted.getNumber());
				e.printStackTrace();
				if (countSteps > 0)
					buildInformationDao.persist(new BuildInformation(build, countSteps, Status.PARCIAL));
				return;
			}

		}
		entityManager.close(); 
	}

	private boolean needToProcess() {
		return hasBuildsToApply() && biggerThanLastAppliedBuild();
	}

	private boolean hasBuildsToApply() {
		return !this.builds.isEmpty();
	}

	private boolean biggerThanLastAppliedBuild() {
		if (!hasBuildApplied()) {
			return true;
		}
		return builds.get(builds.size() - 1).compareTo(this.lastAppliedBuild) > 0;
	}

	private boolean hasBuildApplied() {
		return this.lastAppliedBuild != null;
	}

}
