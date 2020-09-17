package view;

import javax.swing.JButton;

import model.Tasks;

/**
 * A class to model a Task button, a JBUtton with access to the Task it was created with
 * @author Miranda Bessex 6/11/19
 */
public class TaskButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	/** The Task the button was created with*/
	private Tasks myTask;
	
	/**
	 * Constructor for the Task Button
	 * @param theProject
	 * @author Miranda Bessex 6/11/19
	 */
	public TaskButton(Tasks theTask, String theString) {
		super(theString);
		myTask = theTask;
	
	}
	
	/**
	 * A getter for the task on the button
	 * @author Miranda Bessex 6/11/19
	 */
	public Tasks getTask() {
		return myTask;
	}
	
	/**
	 * Setter for the task
	 * @author Miranda Bessex 6/11/19
	 */
	public void setTask(Tasks theTask) {
		myTask = theTask;
	}
}
