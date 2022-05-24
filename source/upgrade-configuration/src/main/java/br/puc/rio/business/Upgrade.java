package br.puc.rio.business;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.Status;
import br.puc.rio.model.Step;
import br.puc.rio.model.UpgradeConfiguration;

public class Upgrade implements Update {
	UpgradeConfiguration upgradeConfiguration;
	private EntityManager entityManager;
	private BuildInformationDao buildInformationDao;

	public Upgrade(UpgradeConfiguration upgradeConfiguration, EntityManager entityManager) {
		this.upgradeConfiguration = upgradeConfiguration;
		this.entityManager = entityManager;
		this.buildInformationDao = new BuildInformationDao(entityManager);
	}

	public void execute() {
		List<Build> builds = upgradeConfiguration.getBuilds();
		if (builds == null || builds.isEmpty())
			return;

		Collections.sort(builds);
		BuildInformation lastBuildInformation = buildInformationDao.getLastBuildInformation();
		
		if(lastBuildInformation!=null && builds.get(builds.size()-1).compareTo(lastBuildInformation.getBuild())<0) 
			return;
		
		int builIndex = 0;
		int stepIndex = 0;

		if (lastBuildInformation != null && builds.contains(lastBuildInformation.getBuild())) {
			builIndex = builds.indexOf(lastBuildInformation.getBuild());
			if (lastBuildInformation.getStatus() == Status.COMPLETE) {
				builIndex++;
			} else {
				stepIndex = lastBuildInformation.getSteps();
			}
		}

		for (int i = builIndex; i < builds.size(); i++) {
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
				buildInformationDao.persist(new BuildInformation(build, build.getSteps().size(),Status.COMPLETE));
			} catch (Exception e) {
				System.out.println("Build execution error:" + build + " " + "Step:" + lastStepExecuted.getNumber());
				e.printStackTrace();
				if(countSteps > 0)
					buildInformationDao.persist(new BuildInformation(build,countSteps,Status.PARCIAL));
				return;
			}
			
		}
		entityManager.close();
	}
}
