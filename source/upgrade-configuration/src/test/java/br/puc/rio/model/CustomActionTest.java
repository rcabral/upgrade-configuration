package br.puc.rio.model;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomActionTest {
	private CustomAction customAction;

	@Before
	public void setUp() {
		String className = "br.puc.rio.sample.HelloCustomAction";
		customAction = new CustomAction(className);
	}
	
	
	@Mock
	private EntityManager entityManager;

	@Test
	public final void testGetActionType() {
		assertEquals(ActionType.CUSTOM, customAction.getActionType());
	}

	@Test(expected = Test.None.class)
	public final void testExecute() throws Exception {
		customAction.execute(entityManager);
	}

}
