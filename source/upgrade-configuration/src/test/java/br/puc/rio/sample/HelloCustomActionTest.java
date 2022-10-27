package br.puc.rio.sample;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloCustomActionTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private EntityManager entityManager;
	
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
	public void printHelloCustomActionTest() throws Exception {
	    new HelloCustomAction().execute(entityManager);
	    String content = outContent.toString().replace("\n", "").replace("\r", "");
	    assertEquals("Hello Custom Action! Here you can put your arbitrary code.", content);
	}

}
