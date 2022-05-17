package br.puc.rio;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.persistence.EntityManager;

import com.thoughtworks.xstream.XStream;

import br.puc.rio.business.Upgrade;
import br.puc.rio.model.UpgradeConfiguration;
import br.puc.rio.util.JPAUtil;

public class UpgradeConfigurationXMLTest {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Begin");
		EntityManager entityManager = JPAUtil.criaEntityManager();
		FileReader fileReader = new FileReader("upgrade-configuration.xml");
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		xStream.alias("UpgradeConfiguration", UpgradeConfiguration.class);
		UpgradeConfiguration upgradeConfiguration = (UpgradeConfiguration) xStream.fromXML(fileReader);
		Upgrade upgrade = new Upgrade(upgradeConfiguration, entityManager);
		upgrade.execute();
		System.out.println("End");
	}
}
