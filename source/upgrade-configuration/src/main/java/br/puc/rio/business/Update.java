package br.puc.rio.business;

import javax.persistence.EntityManager;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.UpgradeConfiguration;

public interface Update {
	public void execute();
	public static Update create(UpgradeConfiguration upgradeConfiguration, EntityManager entityManager, BuildInformationDao buildInformationDao) {
		if(upgradeConfiguration.isDowngrade()) {
			return new Downgrade(upgradeConfiguration, entityManager, buildInformationDao);
		}
		return new Upgrade(upgradeConfiguration, entityManager,buildInformationDao);
	}

}
