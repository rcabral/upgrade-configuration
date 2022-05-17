package br.puc.rio.model;

import javax.persistence.EntityManager;

public class CustomAction implements Action {
	private String className;
	
	public CustomAction(String className) {
		super();
		this.className = className;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.CUSTOM;
	}

	@Override
	public void execute(EntityManager entityManager) {
		// TODO Auto-generated method stub
		
	}

}
