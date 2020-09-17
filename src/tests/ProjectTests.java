/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Materials;
import model.Project;
import model.Tasks;



/**
 * @author Joseph Rushford 6/13/19
 */
public class ProjectTests {

	private Project myTestProject;
	
	
	@Before
	public void setUp() {
		ArrayList<Tasks> temp = new ArrayList<Tasks>();
		temp.add(new Tasks("hello", true));
		ArrayList<Materials> mat = new ArrayList<Materials>();
		mat.add(new Materials("test", 4, 6));
		myTestProject = new Project(34, "why", 3, 7, temp, mat);
	}
	
	/**
	 * @author Joseph Rushford 6/13/19
	 * Test method for Project toString method
	 */
	@Test
	public void testToString() {
		assertEquals("why     Priority: 7     Difficulty: 3     ", myTestProject.toString());
	}
	
	/**
	 * @author Miranda Bessex 6/13/19
	 * Test method for Project getTotalCost method
	 */
	@Test
	public void testgetTotal() {
		assertTrue(Math.abs(myTestProject.getTotalCost() - 24) < 0.00001);
	}
	
	
	
	

}
