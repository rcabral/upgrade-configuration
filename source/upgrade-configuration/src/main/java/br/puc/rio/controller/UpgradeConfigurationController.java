package br.puc.rio.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.persistence.EntityManager;

import com.thoughtworks.xstream.XStream;

import br.puc.rio.business.Update;
import br.puc.rio.model.UpgradeConfiguration;
import br.puc.rio.util.JPAUtil;

public class UpgradeConfigurationController {
	
	public static void execute() throws FileNotFoundException {
		System.out.println("Begin");
		EntityManager entityManager = JPAUtil.criaEntityManager();
		FileReader fileReader = new FileReader("upgrade-configuration.xml");
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		xStream.alias("UpgradeConfiguration", UpgradeConfiguration.class);
		UpgradeConfiguration upgradeConfiguration = (UpgradeConfiguration) xStream.fromXML(fileReader);
		Update update = Update.create(upgradeConfiguration, entityManager);
		update.execute();
		System.out.println("End");
	}

	

}
