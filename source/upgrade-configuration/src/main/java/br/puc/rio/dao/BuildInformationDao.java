package br.puc.rio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;



public class BuildInformationDao {
	
	private final EntityManager em;

	public BuildInformationDao (EntityManager em) {
		this.em = em;
	}
	
	public void persist(BuildInformation buildInformation) {
		em.getTransaction().begin();
		em.persist(buildInformation);
		em.getTransaction().commit();
	}
	
	public void merge(BuildInformation buildInformation) {
		em.getTransaction().begin();
		em.merge(buildInformation);
		em.getTransaction().commit();
	}
		
	public BuildInformation getLastBuildInformation() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("from BuildInformation ");
			sql.append(" order by buildSequence, buildNumber, releaseVersion, minorVersion, majorVersion desc  ");
			return em.createQuery(sql.toString(),BuildInformation.class).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Build getLastBuild() {
		BuildInformation lastBuildInformation = getLastBuildInformation();
		if(lastBuildInformation!=null) {
			return lastBuildInformation.getBuild();
		}else {
			return null;
		}
	}

}
