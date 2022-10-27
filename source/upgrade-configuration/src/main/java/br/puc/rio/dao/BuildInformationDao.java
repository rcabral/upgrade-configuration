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


/**
 * 
 * Class used to allow the persistence of the BuildInformation instance.
 *
 */
public class BuildInformationDao {
	
	private final EntityManager em;
	
	/**
	 * Constructor Method
	 * @param em
	 */
	public BuildInformationDao (EntityManager em) {
		this.em = em;
	}
	
	/**
	 * Persist a buildInformation.
	 * @param buildInformation
	 */
	public void persist(BuildInformation buildInformation) {
		em.getTransaction().begin();
		em.persist(buildInformation);
		em.getTransaction().commit();
	}
	
	/**
	 * Merge a buildInformation.
	 * @param buildInformation
	 */
	public void merge(BuildInformation buildInformation) {
		em.getTransaction().begin();
		em.merge(buildInformation);
		em.getTransaction().commit();
	}
	
	/**
	 * Delete a buildInformation.
	 * @param buildInformation
	 */
	public void delete(BuildInformation buildInformation) {
		em.getTransaction().begin();
		em.remove(buildInformation);
		em.getTransaction().commit();
	}
	
	/**
	 * Returns the last applied Build.
	 * @return Build
	 */
	public Build getLastBuild() {
		BuildInformation lastBuildInformation = getLastBuildInformation();
		if(lastBuildInformation!=null) {
			return lastBuildInformation.getBuild();
		}else {
			return null;
		}
	}
	
	/**
	 * Returns the last applied buildInformation.
	 * @return BuildInformation
	 */
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
	
	/**
	 * Return a BuildInformation from a instance of a Build applied.
	 * @param build
	 * @return BuildInformation
	 */
	public Optional<BuildInformation> getBuildInformation(Build build) {
		return getBuildInformation(new BuildInformation(build));
	}
	
	/**
	 * Return a BuildInformation from a instance of a BuildInformation applied.
	 * @param buildInformation
	 * @return BuildInformation
	 */
	public Optional<BuildInformation> getBuildInformation(BuildInformation buildInformation) {
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
			query.setParameter("majorVersion", buildInformation.getMajorVersion());
			query.setParameter("minorVersion", buildInformation.getMinorVersion() );
			query.setParameter("releaseVersion", buildInformation.getReleaseVersion());
			query.setParameter("buildNumber", buildInformation.getBuildNumber());
			query.setParameter("buildSequence", buildInformation.getBuildSequence());
			return Optional.of(query.setMaxResults(1).getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
	
	/**
	 * Get a list of all BuildInformations applied.
	 * @return List<BuildInformation>
	 */
	public List<BuildInformation> getAppliedBuilds() {
		StringBuilder sql = new StringBuilder();
		sql.append("from BuildInformation ");
		sql.append(" order by majorVersion desc, minorVersion desc, releaseVersion desc, buildNumber desc, buildSequence desc  ");
		return em.createQuery(sql.toString(),BuildInformation.class).getResultList();
	}
	
	
	/**
	 * Update remaining steps from a BuildInformation.
	 * @param buildInformation
	 * @param steps
	 */
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
