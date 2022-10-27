package br.puc.rio.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.persistence.EntityManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

import br.puc.rio.business.Update;
import br.puc.rio.dao.BuildInformationDao;
import br.puc.rio.model.UpgradeConfiguration;
import br.puc.rio.util.JPAUtil;

/**
 * Class used as Controller, the gateway to start the process of Update.
 */
public class UpgradeConfigurationController {
	
	private static final Logger logger = LogManager.getLogger(UpgradeConfigurationController.class);  
	
	/**
	 * Method used to start the update process.
	 * @throws FileNotFoundException - When upgrade-configuration.xml is not found in the project root directory.
	 */
	public static void execute() throws FileNotFoundException {
		logger.info("Initializing update process");
		EntityManager entityManager = JPAUtil.createEntityManager();
		FileReader fileReader = new FileReader("upgrade-configuration.xml");
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		xStream.alias("UpgradeConfiguration", UpgradeConfiguration.class);
		UpgradeConfiguration upgradeConfiguration = (UpgradeConfiguration) xStream.fromXML(fileReader);
		BuildInformationDao buildInformationDao = new BuildInformationDao(entityManager);
		Update update = Update.create(upgradeConfiguration,entityManager,buildInformationDao);
		update.execute();
		logger.info("Update process finished");
	}

	

}
