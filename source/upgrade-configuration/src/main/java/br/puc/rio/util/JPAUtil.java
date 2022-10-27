package br.puc.rio.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Java Persistence API utility class.
 *
 */
public class JPAUtil {
	
	/**
	 * Create an instance of EntityManager.	
	 * @return EntityManager
	 */
	public static EntityManager createEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		return factory.createEntityManager();
	}
}
