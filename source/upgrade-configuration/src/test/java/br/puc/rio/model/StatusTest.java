package br.puc.rio.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatusTest {
	@Test
	public final void testGetDescription() {
		assertEquals("complete", Status.COMPLETE.getDescription());
	}

}
