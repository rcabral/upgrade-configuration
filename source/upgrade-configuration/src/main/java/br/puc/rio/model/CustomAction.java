package br.puc.rio.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
		try {
			Class<?> cls = Class.forName(className);
		    Method meth = cls.getMethod("main", String[].class);
		    String[] params = null; 
		    meth.invoke(null, (Object) params); 
		} catch (IllegalAccessException| IllegalArgumentException |InvocationTargetException |NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
