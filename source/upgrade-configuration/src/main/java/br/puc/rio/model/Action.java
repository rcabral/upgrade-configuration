package br.puc.rio.model;

import javax.persistence.EntityManager;

/**
 * Interface used to create a contract for the action.
 *
 */
public interface Action {
	
	/**
	 * Get action type.
	 * @return ActionType
	 */
	ActionType getActionType();
	
	/**
	 * Method to execute the Action.
	 * @param entityManager
	 * @throws Exception
	 */
	void execute(EntityManager entityManager) throws Exception;
}
