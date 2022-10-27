package br.puc.rio.business;

import javax.persistence.EntityManager;

import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.UpgradeConfiguration;

/**
 * 
 * Interface used to create a contract for the Update process.
 *
 */
public interface Update {
	/**
	 * Method used to execute the Update process.	
	 */
	void execute();
	
	/**
	 * Static method to create a implementation of Update.
	 * @param upgradeConfiguration
	 * @param entityManager
	 * @param buildInformationDao
	 * @return implementation of Update. 
	 * If upgradeConfiguration is Downgrade true,
	 * returns a Downgrade implementation,
	 * otherwise returns an Upgrade implementation.
	 */
	static Update create(UpgradeConfiguration upgradeConfiguration, EntityManager entityManager, BuildInformationDao buildInformationDao) {
		if(upgradeConfiguration.isDowngrade()) {
			return new Downgrade(upgradeConfiguration, entityManager, buildInformationDao);
		}
		return new Upgrade(upgradeConfiguration, entityManager,buildInformationDao);
	}

}
