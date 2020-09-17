package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Materials;

/**
 * Test class for Materials.java
 * @author Jacob Marquardt
 * @version 6/13/19
 */
public class MaterialsTests {
	
	/**
	 * Instance of Materials to test with.
	 */
	private Materials mat;

	/**
	 * Instantiates mat for testing.
	 * @throws Exception
	 * @author Jacob Marquardt
	 * @version 6/13/19
	 */
	@Before
	public void setUp() throws Exception {
		mat = new Materials("material1", 50.00, 5);
	}

	/**
	 * Tests getName() method from Materials.java.
	 * @author Jacob Marquardt
	 * @version 6/13/19
	 */
	@Test
	public void testGetName() {
		assertEquals("material1", mat.getName());
	}

	/**
	 * Tests getCost() method from Materials.java.
	 * @author Jacob Marquardt
	 * @version 6/13/19
	 */
	@Test
	public void testGetCost() {
		assertTrue(Math.abs(mat.getCost() - 50) < 0.0001);
	}

	/**
	 * Tests getQuantity() method from Materials.java.
	 * @author Jacob Marquardt
	 * @version 6/13/19
	 */
	@Test
	public void testGetQuantity() {
		assertEquals(5, mat.getQuantity());
	}

	/**
	 * Tests getTotalCost() method from Materials.java.
	 * @author Jacob Marquardt
	 * @version 6/13/19
	 */
	@Test
	public void testGetTotalCost() {
		assertTrue(Math.abs(mat.getTotalCost() - 250) < 0.0001);
	}

	/**
	 * Tests setName() method from Materials.java.
	 * @author Jacob Marquardt
	 * @version 6/13/19
	 */
	@Test
	public void testSetName() {
		mat.setName("testName");
		assertEquals("testName", mat.getName());
	}

	/**
	 * Tests setCost() method from Materials.java.
	 * @author Jacob Marquardt
	 * @version 6/13/19
	 */
	@Test
	public void testSetCost() {
		mat.setCost(5.00);
		assertTrue(Math.abs(mat.getCost() - 5) < 0.0001);
	}

	/**
	 * Tests setQuantity() method from Materials.java.
	 * @author Jacob Marquardt
	 * @version 6/13/19
	 */
	@Test
	public void testSetQuantity() {
		mat.setQuantity(10);
		assertEquals(10, mat.getQuantity());
	}

}