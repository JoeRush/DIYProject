
/**
 * TCSS 360
 */

package tests;

import static org.junit.Assert.*;

import model.Tasks;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Tasks.java.
 * @author Annora Jones
 * @author Gehry Guest
 * @version 6/12/19
 */
public class TasksTest {

	/**
	 * Task object used in tests.
	 */
	private Tasks task;
	
	/**
	 * Creates the task object. 
	 * @throws java.lang.Exception
	 * @author Gehry Guest
	 * @version 6/12/19
	 */
	@Before
	public void setUp() throws Exception {
		
		task = new Tasks("Build Doghouse", true);
	}

	/**
	 * Test for Task.java getName() method.
	 * @author Gehry Guest
	 * @version 6/12/19
	 */
	@Test
	public void testGetName() {
	
		assertEquals("Build Doghouse", task.getName());
	}
	
	/**
	 * Test for Task.java isCompleted() method.
	 * @author Annora Jones
	 * @version 6/12/19
	 */
	@Test
	public void testIsCompleted() {
		
		assertTrue(task.isCompleted());
	}
	
	/**
	 * Test for Task.java toggleCompletedTask() method.
	 * @author Annora Jones
	 * @version 6/12/19
	 */
	@Test 
	public void testToggleCompletedTask() {
		
		assertTrue(task.isCompleted());
		task.toggleCompletedTask();
		assertFalse(task.isCompleted());
	}
	
	/**
	 * vTest for Task.java setName() method.
	 * @author Annora Jones
	 * @version 6/12/19
	 */
	@Test
	public void testSetName() {
		
		task.setName("Build Nether Portal");
		assertEquals("Build Nether Portal", task.getName());
	}
}
