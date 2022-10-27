package br.puc.rio.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.puc.rio.sample.HelloCustomAction;

public class ActionTest {

	@Test
	public final void testGetActionType() {
		Action action = new HelloCustomAction();
		assertEquals(ActionType.CUSTOM, action.getActionType());
	}

}
