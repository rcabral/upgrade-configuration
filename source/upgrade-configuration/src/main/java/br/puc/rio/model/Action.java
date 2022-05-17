package br.puc.rio.model;

import javax.persistence.EntityManager;

public interface Action {
	public ActionType getActionType();
	public void execute(EntityManager entityManager);
}
