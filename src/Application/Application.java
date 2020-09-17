package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import model.Materials;
import model.Project;
import model.Tasks;
import model.User;

/*
 * Notes:
 * - Operates under the assumption that projectIDs will be in order, starting at 1.
 * - Not made to handle invalid formatting of .csv
 * 				
 * Example structure of .csv file:
 * 
 * - For all boolean values, I used 0 for false and 1 for true.
 * - If a project has no tasks, the task line will just say "notasks"; same for materials but "nomaterials" instead. I did this just for simplicity in the parsing algorithm.
 * 
 * |------------------------------------------------------------------------------------------------------------------------|
 * |username		|																										|
 * |------------------------------------------------------------------------------------------------------------------------|
 * |email			|																										|
 * |------------------------------------------------------------------------------------------------------------------------|
 * |project ID		| project name						| difficulty					| priority	| theStatus (0 or 1)	|
 * |------------------------------------------------------------------------------------------------------------------------|
 * |task 1 name		| task 1 completed or not (0 or 1)	| (repeat for remaining tasks)										|
 * |------------------------------------------------------------------------------------------------------------------------|
 * |material 1 name	| material 1 cost					| material 1 quantity			| (repeat for remaining materials)	|
 * |------------------------------------------------------------------------------------------------------------------------|
 * |(repeat for remaining projects) 																						|
 * |------------------------------------------------------------------------------------------------------------------------|
 */

/**
 * Class that holds username, email, and projects and handles the .csv file where it's all stored.
 * @author Miranda Bessex 6/10/19
 * @author Jacob Marquardt 6/5/19
 */
public class Application {
	/** The user of the application */
	private User myUser;
	
	/** An ArrayList of Projects */
	private ArrayList<Project> theProjects;
	
	/** The persistent saving file while someone is signed in */
	private File csv;
	
	
	/**
	 * Constructor for the application
	 * @param theUser
	 * @author Miranda Bessex 6/10/19
	 */
	public Application(User theUser) {
		theProjects = new ArrayList<Project>();
		myUser = theUser;
		
		//initialized to a dummy value until a file has been loaded
		csv = new File("Not Updated Yet");
	}


	/**
	 * Adds a new project to theProjects
	 * @param theNewProject
	 * @author Jacob Marquardt 6/5/19
	 * @author Miranda Bessex 6/10/19
	 */
	public void addProject(final Project theNewProject) {
		
		//find and delete any existing projects with the same ID reference
		for (Iterator<Project> iterator = theProjects.iterator(); iterator.hasNext(); ) {
    	    Project value = iterator.next();
    	    if (value.getProjectID() == theNewProject.getProjectID()) {
    	        iterator.remove();
    	    }
		}
		
		//Add the project and write to the file
		theProjects.add(theNewProject);
		write();
	}
	
	
	/**
	 * Removes project with the specified ID from theProjects, updates following project IDs to keep ordering correct, and updates csv accordingly.
	 * @param projectID
	 * @author Miranda Bessex 6/10/19
	 */
	public void deleteProject(final int projectID) {
		for (Iterator<Project> iterator = theProjects.iterator(); iterator.hasNext(); ) {
    	    Project value = iterator.next();
    	    if (value.getProjectID() == projectID) {
    	        iterator.remove();
    	    }
		}
		write();
	}
	
	/**
	 * Getter for the projects array
	 * @author Miranda Bessex 6/10/19
	 */
	public ArrayList<Project> getProjectList(){
		return theProjects;
	}
	
	
	/**
	 * Parses the username, email, and projects from a .csv file.
	 * @param theCsv the .csv file being parsed
	 * @author Jacob Marquardt 6/5/19
	 */
	public void loadAllEntries(final File theCsv) {
		try (Scanner scan = new Scanner(theCsv);) {
			if (scan.hasNext()) {
//				Clearing existing projects because they'll be overwritten
				theProjects.clear();

				myUser.setUserName(scan.nextLine().split(",")[0]);
				myUser.setUserEmail(scan.nextLine().split(",")[0]);
				
				String[] line;
				int projectID;
				String projectName;
				int difficulty;
				int priority;
				String[] taskList;
				String[] matList;
				ArrayList<Tasks> tasks = new ArrayList<Tasks>();
				ArrayList<Materials> materials = new ArrayList<Materials>();
				
//				Loop that creates Project objects and adds them to app
				while (scan.hasNextLine()) {
					
//					Parses the basic project info on first line of project
					line = scan.nextLine().split(",");
					projectID = Integer.parseInt(line[0]);
					projectName = line[1];
					difficulty = Integer.parseInt(line[2]);
					priority = Integer.parseInt(line[3]);
					
//					Parses tasks from second line of project
					if (scan.hasNextLine()) {
						taskList = scan.nextLine().split(",");
						if (!taskList[0].equals("notasks")) {
							for (int i = 0; i < taskList.length; i += 2) {
								if (Integer.parseInt(taskList[i + 1]) == 1) {
									tasks.add(new Tasks(taskList[i], true));
								} else {
									tasks.add(new Tasks(taskList[i], false));
								}
							}
						} 
					}
					
//					Parses materials from third line of project
					if (scan.hasNextLine()) {
						matList = scan.nextLine().split(",");
						if (!matList[0].equals("nomaterials")) {
							for (int i = 0; i < matList.length; i += 3) {
								materials.add(new Materials(matList[i], Double.parseDouble(matList[i + 1]), Integer.parseInt(matList[i + 2])));
							}
						}
					}
					
//					Adds project to app and clears tasks & materials to be used for the next project
					theProjects.add(new Project(projectID, projectName, difficulty, priority, new ArrayList<Tasks>(tasks), new ArrayList<Materials>(materials)));
					tasks.clear();
					materials.clear();
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes all current application info to csv.
	 * @author Jacob Marquardt 6/5/19
	 */
	public void write() {
		try (FileWriter writer = new FileWriter(csv);) {
			StringBuilder str = new StringBuilder();
			
//			Add username and email
			str.append(myUser.getUserName()).append("\n");
			str.append(myUser.getUserEmail()).append("\n");
			
//			Iterate over all projects
			for (Project p : theProjects) {
				
//				Add ID, name, difficulty, priority, and 1 or 0 if environmentally friendly or not respectively
				str.append(p.getProjectID()).append(",");
				str.append(p.getProjectName()).append(",");
				str.append(p.getDifficultly()).append(",");
				str.append(p.getPriority()).append(",");
				if (p.getEnviromentallyFriendly()) {
					str.append(1);
				} else {
					str.append(0);
				}
				str.append("\n");
				
//				Add tasks if there are any, or "notasks" if there are not
				if (p.getTasks().size() >= 1) {
//					Iterate over every task in current project
					for (Tasks t : p.getTasks()) {
//						Add name, and 1 or 0 if completed or not respectively
						str.append(t.getName()).append(",");
						if (t.isCompleted()) {
							str.append(1);
						} else {
							str.append(0);
						}
						str.append(",");
					}
				} else {
					str.append("notasks");
				}
				str.append("\n");
//				Add materials if there are any, or "nomaterials" if there are not
				if (p.getMaterials().size() >= 1) {
//					Iterate over over material in current project
					for (Materials m : p.getMaterials()) {
//						Add name, cost, and quantity
						str.append(m.getName()).append(",");
						str.append(m.getCost()).append(",");
						str.append(m.getQuantity()).append(",");
					}
				} else {
					str.append("nomaterials");
				}
				str.append("\n");
			}
//			Write it all to csv
			writer.write(str.toString());
			writer.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
		
	/*
	 * Getters.
	 */
    
	/**
	 * Getter for theProjects.
	 * @return theProjects
	 * @author Jacob Marquardt 6/5/19
	 */
	public ArrayList<Project> getProjects() {
		return theProjects;
	}
	
	/**
	 * Getter for username.
	 * @return username
	 * @author Jacob Marquardt 6/5/19
	 */
	public String getUsername() {
		return myUser.getUserName();
	}
	
	/**
	 * Getter for userEmail.
	 * @return userEmail
	 * @author Jacob Marquardt 6/5/19
	 */
	public String getUserEmail() {
		return myUser.getUserEmail();
	}
	
	/**
	 * Getter for csv
	 * @return csv
	 * @author Jacob Marquardt 6/5/19
	 */
	public File getCsv() {
		return csv;
	}
	
	/*
	 * Setters.
	 */
	
	/**
	 * Setter for the csv file the app is being saved too
	 * @author Miranda Bessex 6/10/19
	 * @params the file
	 */
	public void setFile(final File theFile) {
		csv = theFile;
	}
	
	/**
	 * Changes username to the argument passed and updates csv accordingly.
	 * @param theUsername
	 * @author Jacob Marquardt 6/5/19
	 */
	public void setUserName(final String theUsername) {
		myUser.setUserName(theUsername);
		write();
	}
	
	/**
	 * Changes userEmail to the argument passed and updates csv accordingly.
	 * @param theUserEmail
	 * @author Jacob Marquardt 6/5/19
	 */
	public void setUserEmail(final String theUserEmail) {
		myUser.setUserEmail(theUserEmail);
		write();
	}
}