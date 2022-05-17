package br.puc.rio.business;

import br.puc.rio.model.UpgradeConfiguration;

public class Downgrade implements Update {
	
	private UpgradeConfiguration upgradeConfiguration;

	public Downgrade(UpgradeConfiguration upgradeConfiguration) {
		this.upgradeConfiguration = upgradeConfiguration;
	}

	@Override
	public void execute() {
	}

}
