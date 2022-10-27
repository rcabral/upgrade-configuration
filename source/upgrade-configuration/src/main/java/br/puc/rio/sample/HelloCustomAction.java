package br.puc.rio.sample;

import javax.persistence.EntityManager;

import br.puc.rio.model.Action;

/**
 * A sample Class of a CustomAction implementation.
 */
public class HelloCustomAction implements Action {
	
	@Override
	public void execute(EntityManager entityManager) throws Exception {
		System.out.println("Hello Custom Action! Here you can put your arbitrary code.");
	}

}
