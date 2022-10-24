package br.puc.rio.model;

import javax.persistence.EntityManager;

public interface Action {
	ActionType getActionType();
	void execute(EntityManager entityManager) throws Exception;
}
