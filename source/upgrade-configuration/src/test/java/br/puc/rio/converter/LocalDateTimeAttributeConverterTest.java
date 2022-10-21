package br.puc.rio.converter;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class LocalDateTimeAttributeConverterTest {
	
	private LocalDateTimeAttributeConverter converter;
	
	@Before
	public void setUp() {
		converter = new LocalDateTimeAttributeConverter();
	}

	@Test
	public final void testConvertToDatabaseColumn() {
		assertTrue(converter.convertToDatabaseColumn(LocalDateTime.now()) instanceof Timestamp); 
	}
	
	@Test
	public final void testConvertNullToDatabaseColumn() {
		assertNull(converter.convertToDatabaseColumn(null)); 
	}

	@Test
	public final void testConvertToEntityAttribute() {
		assertTrue(converter.convertToEntityAttribute(Timestamp.valueOf(LocalDateTime.now())) instanceof LocalDateTime); 
	}
	
	@Test
	public final void testConvertNullToEntityAttribute() {
		assertNull(converter.convertToEntityAttribute(null)); 
	}

}
