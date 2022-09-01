package br.puc.rio.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.Step;
import br.puc.rio.model.UpgradeConfiguration;

public class Downgrade implements Update {
	private EntityManager entityManager;
	private BuildInformationDao buildInformationDao;
	private Build lastBuildApplied;
	private List<BuildInformation> appliedBuilds;
	private Build downgradeBuild;
	private List<Build> buildsInformation;
	private List<BuildInformation> buildsToRevert;

	public Downgrade(UpgradeConfiguration upgradeConfiguration, EntityManager entityManager) {
		this.entityManager = entityManager;
		this.buildInformationDao = new BuildInformationDao(entityManager);
		this.appliedBuilds = buildInformationDao.getAppliedBuilds();
		this.lastBuildApplied = buildInformationDao.getLastBuildInformation().getBuild();
		this.downgradeBuild = upgradeConfiguration.getDowngradeBuild().get();
		this.buildsInformation = upgradeConfiguration.getBuilds();
		this.buildsToRevert = getBuildsToRevert(this.appliedBuilds, this.downgradeBuild );
	}

	private List<BuildInformation> getBuildsToRevert(List<BuildInformation> appliedBuilds, Build downgradeBuild){
		List<BuildInformation> buildsToRevert = new ArrayList<>();
		if(this.appliedBuilds.contains(new BuildInformation(downgradeBuild))){
			for (int i = 0; i < this.appliedBuilds.size() && downgradeBuild.compareTo(this.appliedBuilds.get(i)) < 0 ; i++) {
				BuildInformation build = this.appliedBuilds.get(i);
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
		for (BuildInformation buildInformation : this.buildsToRevert) {
			if(buildsInformation.contains(buildInformation.getBuild())) {
				int index = buildsInformation.indexOf(buildInformation.getBuild());
				Build buildToRevert = buildsInformation.get(index);
				List<Step> steps = buildToRevert.getSteps();
				int numberOfSteps = steps.size();
				int stepsExecuted = 0;
				try {
					
					for (int i = numberOfSteps -1; i > -1; i--) {
						Step step = steps.get(i);
						if(step.getDowngradeAction().isPresent())
							step.getDowngradeAction().get().execute(entityManager);
						stepsExecuted ++;
					}
					buildInformationDao.delete(buildInformation);
					System.out.println("Downgrade Build: " + buildToRevert.toString());
				} catch (Exception e) {
					System.out.println("Downgrade Build execution error:" + buildInformation);
					e.printStackTrace();
					int numberOfRemainingSteps = numberOfSteps - stepsExecuted;
					if(numberOfRemainingSteps > 0) {
						buildInformationDao.updateRemainingSteps(buildInformation, numberOfRemainingSteps);
					}						
					return;
				}
			}
		}
	}

	private boolean needToProcess() {
		return outOfDate() && hasBuildsToRevert();
	}

	private boolean hasBuildsToRevert() {
		return !this.buildsToRevert.isEmpty();
	}

	private boolean outOfDate() {
		if(lastBuildApplied.equals(appliedBuilds))
			return false;
		
		return true;
	}
	
	

}
