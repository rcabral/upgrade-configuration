package br.puc.rio.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.Step;
import br.puc.rio.model.UpgradeConfiguration;

/**
 * 
 * Implements Update, to create a upgrade process.
 *
 */
public class Downgrade implements Update {
	private EntityManager entityManager;
	private BuildInformationDao buildInformationDao;
	private List<BuildInformation> appliedBuildsInformation;
	private Build lastBuildApplied;
	private Build downgradeBuild;
	private List<Build> buildsFromXML;
	private List<BuildInformation> buildsInformationToRevert;
	private static final Logger logger = LogManager.getLogger(Downgrade.class);
	
	
	public Downgrade(UpgradeConfiguration upgradeConfiguration, EntityManager entityManager, BuildInformationDao buildInformationDao) {
		this.entityManager = entityManager;
		this.buildInformationDao = buildInformationDao;
		this.appliedBuildsInformation = buildInformationDao.getAppliedBuilds();
		this.lastBuildApplied = buildInformationDao.getLastBuildInformation().getBuild();
		this.downgradeBuild = upgradeConfiguration.getDowngradeBuild().get();
		this.buildsFromXML = upgradeConfiguration.getBuilds();
		this.buildsInformationToRevert = getBuildsInformationToRevert(this.appliedBuildsInformation, this.downgradeBuild );
	} 
	
	private List<BuildInformation> getBuildsInformationToRevert(List<BuildInformation> appliedBuilds, Build downgradeBuild){
		List<BuildInformation> buildsToRevert = new ArrayList<>();
		if(appliedBuilds.contains(new BuildInformation(downgradeBuild))){
			for (int i = 0; i < appliedBuilds.size() && downgradeBuild.compareTo(appliedBuilds.get(i)) < 0 ; i++) {
				BuildInformation build = appliedBuilds.get(i);
				buildsToRevert.add(build);
			}
		}
		return buildsToRevert;
	}
		
	@Override
	public void execute() {
		if (!needToProcess()) {
			return;
		}
		for (BuildInformation buildInformationToRevert : this.buildsInformationToRevert) {
			if(buildsFromXML.contains(buildInformationToRevert.getBuild())) {
				int index = buildsFromXML.indexOf(buildInformationToRevert.getBuild());
				Build buildToRevertFromXML = buildsFromXML.get(index);
				List<Step> steps = buildToRevertFromXML.getSteps();
				int numberOfSteps = steps.size();
				int stepsExecuted = 0;
				try {
					for (int i = numberOfSteps -1; i > -1; i--) {
						Step step = steps.get(i);
						if(step.getDowngradeAction().isPresent())
							step.getDowngradeAction().get().execute(entityManager);
						stepsExecuted ++;
					}
					buildInformationDao.delete(buildInformationToRevert);
					logger.info("Downgrade Build: " + buildToRevertFromXML.toString());
				} catch (Exception e) {
					logger.error("Downgrade Build execution error:" + buildInformationToRevert);
					e.printStackTrace();
					int numberOfRemainingSteps = numberOfSteps - stepsExecuted;
					if(numberOfRemainingSteps > 0) {
						buildInformationDao.updateRemainingSteps(buildInformationToRevert, numberOfRemainingSteps);
					}						
					return;
				}
			}
		}
	}

	private boolean needToProcess() {
		return outOfDate() && hasBuildsToRevert();
	}
	
	private boolean outOfDate() {
		return !lastBuildApplied.equals(downgradeBuild);
	}

	private boolean hasBuildsToRevert() {
		return !this.buildsInformationToRevert.isEmpty();
	}
}
