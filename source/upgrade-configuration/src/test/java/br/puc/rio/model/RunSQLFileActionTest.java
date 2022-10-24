package br.puc.rio.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RunSQLFileActionTest {
	
	private RunSQLFileAction action;
	@Mock
	private EntityManager entityManager;
	@Mock	
	private EntityTransaction transaction;
	@Mock
	private Query query;
	private String path;
	
	@Before
	public void setUp() {
		path = "demo/upgrade-script.sql";
		action = new RunSQLFileAction(path);
	}

	@Test
	public final void testGetActionType() {
		assertEquals(ActionType.RUNSQLFILE, action.getActionType());
	}

	//@Test
	public final void testExecute() throws Exception {
		when(entityManager.getTransaction()).thenReturn(transaction);
		when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
		action.execute(entityManager);
	}
	
	//@Test(expected = RuntimeException.class)
	public final void testExecuteWithRunTimeException() throws Exception {
		when(entityManager.getTransaction()).thenReturn(transaction);
		action.execute(entityManager);
	}

	public RunSQLFileAction getAction() {
		return action;
	}

}
