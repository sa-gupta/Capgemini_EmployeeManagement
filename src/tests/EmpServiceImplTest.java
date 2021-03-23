package com.cg.JunitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.service.EmployeeService;
import com.cg.service.EmployeeServiceImpl;

public class EmpServiceImplTest {

	EmployeeService eService  = new EmployeeServiceImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValidName() {
		boolean success = eService.isValidName("Sa");
		assertTrue(success);
	}

	@Test
	public void testIsValidMobile() {
		boolean success = eService.isValidMobile(9818970361L);
		assertTrue(success);
	}

}
