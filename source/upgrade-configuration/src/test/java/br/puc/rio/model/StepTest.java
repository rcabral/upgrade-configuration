package br.puc.rio.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StepTest {
	
	private Step step;
	private Step otherStep;
	private int number;
	@Mock
	private Action upgrade;
	@Mock
	private Action downgrade;
	
	@Before
	public void setUp() {
		number = 1;
		step = new Step(number,upgrade,downgrade);
		otherStep = new Step(number,upgrade, downgrade);
	}

	@Test
	public final void testEqualsObject() {
		assertTrue(step.equals(otherStep));
	}

	@Test
	public final void testCompareTo() { 
		assertEquals(0, step.compareTo(otherStep));
	}

	@Test
	public final void testGetNumber() {
		assertEquals(number, step.getNumber());
	}

	@Test
	public final void testGetUpgradeAction() {
		assertEquals(upgrade, step.getUpgradeAction());
	}

	@Test
	public final void testGetDowngradeAction() {
		assertEquals(downgrade, step.getDowngradeAction().get());
	}

	@Test
	public final void testToString() {
		assertTrue(step.toString().contains("number=" + number));
	}
	
	public static List<Step> getSteps() {
		return new ArrayList<>();
	}

}
