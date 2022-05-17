package br.puc.rio.model;

import javax.persistence.EntityManager;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class RunSQLAction implements Action{
	private String sql;
		
	public RunSQLAction(String sql) {
		super();
		this.sql = sql;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.RUNSQL;
	}

	@Override
	public void execute(EntityManager entityManager) {
		try {
			entityManager.getTransaction().begin();
			entityManager.createNativeQuery(sql).executeUpdate();
			entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}
}
