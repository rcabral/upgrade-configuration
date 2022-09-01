package br.puc.rio.business;

import javax.persistence.EntityManager;

import br.puc.rio.model.UpgradeConfiguration;

public interface Update {
	public void execute();
	public static Update create(UpgradeConfiguration upgradeConfiguration, EntityManager entityManager) {
		if(upgradeConfiguration.isDowngrade()) {
			return new Downgrade(upgradeConfiguration, entityManager);
		}
		return new Upgrade(upgradeConfiguration, entityManager);
	}

}
