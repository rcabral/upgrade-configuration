package br.puc.rio.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RunSQLActionTest {
	
	private RunSQLAction action;
	@Mock
	private EntityManager entityManager;
	@Mock	
	private EntityTransaction transaction;
	@Mock
	private Query query;
	private String sql;
	

	@Before
	public void setUp() {
		sql = "select * from rom BuildInformation";
		action = new RunSQLAction(sql);
	}

	@Test
	public final void testGetActionType() {
		assertEquals(ActionType.RUNSQL, action.getActionType());
	}

	@Test(expected = Test.None.class)
	public final void testExecute() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		when(entityManager.createNativeQuery(sql)).thenReturn(query);
		action.execute(entityManager);
	}
	
	@Test(expected = RuntimeException.class)
	public final void testExecuteWithRunTimeException() {
		when(entityManager.getTransaction()).thenReturn(transaction);
		action.execute(entityManager);
	}

	public RunSQLAction getAction() {
		return action;
	}


}
