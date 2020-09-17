/**
 * 
 */
package model;

/**
 * @author Annora Jones 5/28/19
 *
 */
public class Tasks {

	/**
	 * The name of the Task.
	 * @author Annora Jones 5/28/19
	 */
	private String name;
	
	/**
	 * Keeps track of whether the Task has been completed or not.
	 * @author Annora Jones 5/28/19
	 */
	private boolean completed;
	
	/**
	 * Constructs a Task object.
	 * @author Annora Jones 5/28/19
	 * @param theName the name of the Task.
	 */
	 public Tasks(final String theName, final boolean isCompleted){
		name = theName;
		completed = isCompleted;
	 }
	 /**
	  * @author Annora Jones 5/28/19

	  */
	 public String getName() {
		 return name;
	 } 
	 /**
	  * @author Annora Jones 5/28/19

	  */
	 public boolean isCompleted() {
		 return completed;
	 }

	
	/**
	 * Toggles completion of Task.
	 * @author Annora Jones 5/28/19

	 */
	public void toggleCompletedTask() {
		
		completed = !completed;
	}
	
	/**
	 * @param theName the name to give to the Task.
	 *  @author Annora Jones 5/28/19
	 */
	public void setName(final String theName) {
		
		name = theName;
	}
	
}
