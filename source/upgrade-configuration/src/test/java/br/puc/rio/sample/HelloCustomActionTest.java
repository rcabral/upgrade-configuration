package br.puc.rio.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloCustomActionTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	
	@Test
	public void printHelloCustomActionTest() {
	    helloCustomAction();
	    assertEquals("Hello Custom Action!", outContent.toString());
	}
	
	@Test
	public void createNewHelloCustomActionTest() {
	    assertTrue(new HelloCustomAction() instanceof HelloCustomAction);
	}

	private void helloCustomAction() {
		HelloCustomAction.main(new String[] {});
	}
	

}
