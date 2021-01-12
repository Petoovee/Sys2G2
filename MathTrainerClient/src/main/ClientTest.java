package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Buffer;
import entity.Person;


class ClientTest {
	@BeforeEach
	void setUp() throws Exception {
	}
	@AfterEach
	void tearDown() throws Exception { 

	}
	
	@Test
	void testPerson() {
		Person testPerson = new Person();
		testPerson.setFirstName("Tester");
		assertEquals("Tester", testPerson.getFirstName());
	}
	
	@Test
	void testBuffer() {
		Buffer<String> testBuffer = new Buffer<String>();
		testBuffer.put("Tester1");
		testBuffer.put("Tester2");
		try {
			assertEquals("Tester1", testBuffer.get());
			assertEquals("Tester2", testBuffer.get());
		} catch (InterruptedException e) {
			fail("Gould not get() from entity/Buffer");
			e.printStackTrace();
		}
	}
	
	// Tests for future 
}
