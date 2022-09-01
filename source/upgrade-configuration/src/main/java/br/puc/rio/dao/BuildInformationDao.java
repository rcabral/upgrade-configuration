package br.puc.rio.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.puc.rio.model.Build;
import br.puc.rio.model.BuildInformation;
import br.puc.rio.model.Status;



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
	
	public void delete(BuildInformation buildInformation) {
		em.getTransaction().begin();
		em.remove(buildInformation);
		em.getTransaction().commit();
	}
		
	public BuildInformation getLastBuildInformation() {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("from BuildInformation ");
			sql.append(" order by majorVersion desc, minorVersion desc, releaseVersion desc, buildNumber desc, buildSequence desc  ");
			return em.createQuery(sql.toString(),BuildInformation.class).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Optional<BuildInformation> getBuildInformation(Build build) {
		return getBuildInformation(new BuildInformation(build));
	}
	
	public Optional<BuildInformation> getBuildInformation(BuildInformation build) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("from BuildInformation ");
			sql.append(" where ");
			sql.append(" majorVersion = :majorVersion ");
			sql.append(" and  minorVersion = :minorVersion ");
			sql.append(" and  releaseVersion = :releaseVersion ");
			sql.append(" and  buildNumber = :buildNumber ");
			sql.append(" and  buildSequence = :buildSequence ");
			TypedQuery<BuildInformation> query = em.createQuery(sql.toString(),BuildInformation.class);
			query.setParameter("majorVersion", build.getMajorVersion());
			query.setParameter("minorVersion", build.getMinorVersion() );
			query.setParameter("releaseVersion", build.getReleaseVersion());
			query.setParameter("buildNumber", build.getBuildNumber());
			query.setParameter("buildSequence", build.getBuildSequence());
			return Optional.of(query.setMaxResults(1).getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
	
	public List<BuildInformation> getAppliedBuilds() {
		StringBuilder sql = new StringBuilder();
		sql.append("from BuildInformation ");
		sql.append(" order by majorVersion desc, minorVersion desc, releaseVersion desc, buildNumber desc, buildSequence desc  ");
		return em.createQuery(sql.toString(),BuildInformation.class).getResultList();
	}
	
	public Build getLastBuild() {
		BuildInformation lastBuildInformation = getLastBuildInformation();
		if(lastBuildInformation!=null) {
			return lastBuildInformation.getBuild();
		}else {
			return null;
		}
	}
	
	public void updateRemainingSteps(BuildInformation buildInformation, int steps) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update BuildInformation  ");
		sql.append(" set steps = :steps , status = :status, upgradeDate = :upgradeDate  ");
		sql.append(" where id = :id  ");
		Query query = em.createQuery(sql.toString());
		query.setParameter("id",buildInformation.getId());
		query.setParameter("steps", steps);
		query.setParameter("status", Status.PARCIAL);
		query.setParameter("upgradeDate", LocalDateTime.now());
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
	}

}
