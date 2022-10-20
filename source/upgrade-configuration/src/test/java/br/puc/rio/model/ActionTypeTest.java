package br.puc.rio.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActionTypeTest {

	@Test
	public final void testGetDescription() {
		assertEquals("RunSql", ActionType.RUNSQL.getDescription());
	}

}
