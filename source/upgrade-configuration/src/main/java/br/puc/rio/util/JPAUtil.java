package br.puc.rio.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManager criaEntityManager() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("default");
		return factory.createEntityManager();
	}
}
