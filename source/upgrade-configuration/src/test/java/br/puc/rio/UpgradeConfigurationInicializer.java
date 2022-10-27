package br.puc.rio;
import java.io.FileNotFoundException;

import br.puc.rio.controller.UpgradeConfigurationController;

/**
 * Class that should be used to start the Update process.
 */
public class UpgradeConfigurationInicializer {
	
	/** Main method used start the update process.
	 * @param args - Parameter not used, but required for the default signature of the main method.
	 * @throws FileNotFoundException - When upgrade-configuration.xml is not found in the project root directory.  
	 */
	public static void main(String[] args) throws FileNotFoundException {
		UpgradeConfigurationController.execute();
	}
	
}
