package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Application.Application;
import model.Materials;
import model.Project;
import model.Tasks;

/**
 * Class to model the project view/edit page
 * @author Miranda Bessex 6/5/19
 * @author Gehry Guest 6/2/19
 * @author Joseph Rushford 5/27/19
 */
public class ProjectPage extends JFrame implements ChangeListener, ActionListener {
	
    private static final long serialVersionUID = 1304544587598674961L;

    /** The width of the panel. */
    private static final int WIDTH = 600;

    /** The height of the panel. */
    private static final int HEIGHT = 700;
    
    /** Integer value to hold difficulty level. */
    private static int myDifficultyValue;
    
    /** The slider for difficulty of project. */
    private JSlider myDifficultySlider;
    
    /** Integer value to hold difficulty level. */
    private static int myPriortyValue;
    
    /** The slider for difficulty of project. */
    private JSlider myPriortySlider;
    
    /** The edit button */
    private JButton myEditButton;
    
    /** The cancel button */
    private JButton myCancelButton;
    
    /** The delete button */
    private JButton myDeleteButton;
    
    /** The Save button */
    private JButton mySaveButton;
    
    /** The material display panel */
    private JPanel myMatPan;
    
    /** The tasks display panel */
    private JPanel myTaskPan;
    
    /** The name of the project when editable */
    private JTextField myName;
    
    /** The reference to the project being displayed*/
    private Project myProject;
    
    /**A collection of all of the buttons */
    private JPanel buttonOptions;
    
    /** The home page reference */
    private HomePage myHome;
    
    /** The tab object that holds the materials and tasks */
    private JTabbedPane myTab;

    /** Button to add Tasks */
	private JButton myTaskAdd;
	
	/** Button to add Materials */
	private JButton myMatAdd;
	
	/**Reference to the application model */
    private Application myApp;
    
    /**Reference to self */
    private ProjectPage myProjectPage;
	
	
	/**
	 * Constructor for a project Page
	 * @param theHome
	 * @param theApp
	 * @param theProject
	 * @author Gehry Guest 6/2/19
	 * @author Joseph Rushford 5/27/19
	 */
    public ProjectPage(HomePage theHome, Application theApp, Project theProject) {
    	
    	myApp = theApp;
        myHome = theHome;
        myProject = theProject;
        myProjectPage = this;
        
    	initializeFields();
    	
    	createSliders();
        
        createTabPanes();
    	
    	setUpProjectPage();     
    
    }
    
    /**
     * Method to set up the project page
     * @author Miranda Bessex 6/05/19
	 * @author Gehry Guest 6/03/19
     */
    private void setUpProjectPage() {
    	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	final Container container = getContentPane();
    	
    	final JPanel panel = new JPanel(new GridLayout(4, 1));
    	container.add(panel, BorderLayout.CENTER);
        panel.setOpaque(true);
        
        final JLabel imgLabel = new JLabel("",
                new ImageIcon(getClass().getClassLoader().getResource("App Logo_without name.png")), 
                SwingConstants.CENTER); 
        
        panel.add(imgLabel);
        
        formatNameField();
        panel.add(myName);
           
        JPanel addStuffButtons = new JPanel();
        
        myMatAdd.addActionListener(this);
        addStuffButtons.add(myMatAdd, BorderLayout.WEST);

        myTaskAdd.addActionListener(this);
        addStuffButtons.add(myTaskAdd, BorderLayout.EAST);
          
        //Panel to hold both sliders
        JPanel sliderPanel = new JPanel(new BorderLayout());
        
        //Difficulty slider 
        JPanel diffPan = new JPanel();
        diffPan.add(new JLabel("Difficulty"));
        diffPan.add(myDifficultySlider);
        myDifficultySlider.setValue(myProject.getDifficultly());
        sliderPanel.add(diffPan, BorderLayout.WEST);
        
        //priority slider
        JPanel priorPan = new JPanel();
        priorPan.add(new JLabel("Priorty"));
        priorPan.add(myPriortySlider);
        myPriortySlider.setValue(myProject.getPriority());
        sliderPanel.add(priorPan, BorderLayout.EAST);
        
        //Addd slider panel to main panel
        panel.add(sliderPanel);
        
        //Add the material and task buttons to the main panel
        panel.add(addStuffButtons);
 
        final JPanel buttonAndTabPanel = new JPanel(new BorderLayout());
        
        buttonOptions  = new JPanel();
        
        buttonOptions.add(myEditButton);
        buttonOptions.add(myCancelButton);
        buttonOptions.add(myDeleteButton);
        
        buttonAndTabPanel.add(myTab, BorderLayout.NORTH);
        buttonAndTabPanel.add(buttonOptions, BorderLayout.SOUTH);
        
        container.add(buttonAndTabPanel, BorderLayout.SOUTH);
        
        setState(false);
        
        myEditButton.addActionListener(this);
        myCancelButton.addActionListener(this);
        myDeleteButton.addActionListener(this);
        mySaveButton.addActionListener(this);
        
        this.pack();
        
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

        this.setVisible(true);
		
	}

    /**
     * Method to initialize the formatting for the project name header
     * @author Miranda Bessex 6/7/19
     */
	private void formatNameField() {
		myName.setHorizontalAlignment(SwingConstants.CENTER);
		myName.setBackground(Color.LIGHT_GRAY);
		myName.setOpaque(true);
		myName.setFont(new Font("Arial", Font.BOLD, 20));
	}

	/**
	 * Method for initializing all of the fields for the project page
	 * @author Miranda Bessex 6/7/19
	 */
	private void initializeFields() {
		myName = new JTextField(myProject.getProjectName());
		
		myMatPan = new JPanel(new GridLayout(50, 1));
		myTaskPan = new JPanel(new GridLayout(50, 1));
		
        myTab = new JTabbedPane();
		
		myEditButton = new JButton("Edit");
	    myCancelButton = new JButton("Cancel");
        myDeleteButton = new JButton("Delete");
        mySaveButton = new JButton("Save");
        myMatAdd = new JButton("Add Materials");
        myTaskAdd = new JButton("Add Tasks");
	}

	/**
     * 
     * @author @author Gehry Guest 6/2/19
     */
    public void createSliders() {
        final int increment = 5;
           
        final int maxNumber = 10;

        myDifficultySlider = new JSlider(SwingConstants.HORIZONTAL, 0, maxNumber, increment);
           
        myDifficultySlider.setMajorTickSpacing(increment);
           
        myDifficultySlider.setMinorTickSpacing(1);
           
        myDifficultySlider.setPaintLabels(true);
           
        myDifficultySlider.setPaintTicks(true);
           
        myDifficultySlider.addChangeListener(this);
           
        myDifficultyValue = increment;
        
        myPriortySlider = new JSlider(SwingConstants.HORIZONTAL, 0, maxNumber, increment);
        
        myPriortySlider.setMajorTickSpacing(increment);
           
        myPriortySlider.setMinorTickSpacing(1);
           
        myPriortySlider.setPaintLabels(true);
           
        myPriortySlider.setPaintTicks(true);
       
        myPriortySlider.addChangeListener(this);
        
        myPriortyValue = increment;
    }
    
	/**
	 * Method to create the material and task tabs on the page
	 * @author Joseph Rushford 5/27/19
	 * @author Miranda Bessex 5/27/19
	 */
    public void createTabPanes() {
    	JScrollPane myMats = new JScrollPane(myMatPan);
    	JScrollPane myTsks = new JScrollPane(myTaskPan);
    	
    	myTab.setPreferredSize(new Dimension(500, 250));
    	fillTasks();
    	fillMaterials();
    	myTab.add("Materials", myMats);
    	myTab.add("Tasks", myTsks);
    }

    /**
     * Method to fill the materials panel with the projects materials
     * @author Miranda Bessex 6/6/19
     */
   private void fillMaterials() {
	   for(Materials m: myProject.getMaterials()) {
		   String matInfo = "	Name: " + m.getName() + "       Cost: " + 
					Double.toString(m.getCost()) + "       Quantity: " + 
					Integer.toString(m.getQuantity());
		   
			MaterialButton newLab = new MaterialButton(m, matInfo);
			newLab.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					MaterialEditPanel addMat = new MaterialEditPanel(newLab.getMaterials().getName(), Double.toString(newLab.getMaterials().getCost()), Integer.toString(newLab.getMaterials().getQuantity()), myProjectPage);
					Materials newMaterial = addMat.returnMat();
					
					if(!newMaterial.getName().equals("invalid")) {
						
						deleteMaterial(newMaterial);
						addMaterial(newMaterial);
						
						String matInfo = "	Name: " + newMaterial.getName() + "       Cost: " + 
								Double.toString(newMaterial.getCost()) + "       Quantity: " + 
								Integer.toString(newMaterial.getQuantity());
						
						newLab.setMaterial(newMaterial);
						newLab.setText(matInfo);
					}else{
						myMatPan.remove(newLab);
					}
					
					
				
					myMatPan.repaint();
					myMatPan.revalidate();
				}
			});
			
			myMatPan.add(newLab);
	   }
	   
	   myMatPan.repaint();
	   myMatPan.revalidate();
	}

   /**
    * Method to fill the task panel with the projects tasks
    * @author Miranda Bessex 6/6/19
    */
   private void fillTasks() {
	   for(Tasks t: myProject.getTasks()) {
		   String taskInfo;
			if(t.isCompleted()) {
				taskInfo = "	Name: " + t.getName() + "    Completed: Yes";
			}else {
				taskInfo = "	Name: " + t.getName() + "    Completed: No";
			}

		   TaskButton newTaskButton = new TaskButton(t, taskInfo);
			
			newTaskButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					TaskEditPanel addTask = new TaskEditPanel(newTaskButton.getTask().getName(), newTaskButton.getTask().isCompleted(), myProjectPage);
					
					Tasks newTasks = addTask.returnTask();
					
					if(!newTasks.getName().equals("No Name Given")) {
						
						deleteTask(newTaskButton.getTask());
						
						String TaskInfo2;
						if(newTasks.isCompleted()) {
							TaskInfo2 = "	Name: " + newTasks.getName() + "    Completed: Yes";
						}else {
							TaskInfo2 = "	Name: " + newTasks.getName() + "    Completed: No";
						}
						
						newTaskButton.setTask(newTasks);
						newTaskButton.setText(TaskInfo2);
					}else{
						myTaskPan.remove(newTaskButton);
					}
					
					myTaskPan.repaint();
					myTaskPan.revalidate();
				}
				
			});
			
			//Add to the panel
			myTaskPan.add(newTaskButton);
	   }
		
		myTaskPan.repaint();
		myTaskPan.revalidate();
	}
   
   /**
    * Method to rebuild buttons after edit has been selected
    * @author Miranda Bessex 6/6/19
    */
   private void rebuildButtons() {
	   buttonOptions.removeAll();
	   buttonOptions.add(mySaveButton);
       buttonOptions.add(myCancelButton);
       buttonOptions.add(myDeleteButton);
       buttonOptions.repaint();
       buttonOptions.revalidate();
   }

   /**
    * sets the integer value for difficulty.
    * @author Gehry Guest 6/2/19
    * @param theValue integer for difficulty.
    */
    public void setMyDifficultyValue(final int theValue) {
        myDifficultyValue = theValue;
    }
       
   /**
    * returns a integer value.
    * @author Gehry Guest 6/2/19
    * @return myDifficultyValue integer from slider.
    */
    public int getMyDifficultyValue() {
        return myDifficultyValue;
    }
    
    /**
     * sets the integer value for thickness.
	 * @author Joseph Rushford 5/27/19
     * @param thePriortyValue integer for Proirty.
     */
     public void setMyPriortyValue(final int theValue) {
         myDifficultyValue = theValue;
     }
        
    /**
     * returns a integer value.
	 * @author Joseph Rushford 5/27/19
     * @return Value integer from Priortyslider.
     */
     public int getMyPriortyValue() {
         return myPriortyValue;
     }
     
    /**
     * Method to set the state of the page whether the owner is editing or not
     * @author Gehry Guest 5/27/19
     * @author Joseph Rushford 5/27/19
     */
    public void setState(boolean theState) {
        myDifficultySlider.setEnabled(theState);
        myPriortySlider.setEnabled(theState);
    	myName.setEnabled(theState);
    	myMatAdd.setEnabled(theState);
    	myTaskAdd.setEnabled(theState);

    }
    @Override
    public void stateChanged(final ChangeEvent theEvent) {
    	if(theEvent.getSource() == myDifficultySlider) {
    		setMyDifficultyValue(myDifficultySlider.getValue());
    		myProject.setDifficultly(myDifficultySlider.getValue());
    	} else if( theEvent.getSource() == myPriortySlider) {
    		setMyPriortyValue(myPriortySlider.getValue());
    		myProject.setPriority(myPriortySlider.getValue());
    	}
    }
    
	/**
	 * Action performed override function
	 * Directs the specific actions to different functions 
	 * @author Joseph Rushford 5/28/19
	 * @author Miranda Bessex 6/5/19
	 */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
    	//If the edit button was selected
        if (theEvent.getSource() == myEditButton) {
        	setState(true);
        	rebuildButtons();

        //If the cancel button was selected
        } else if (theEvent.getSource() == myCancelButton) {
            this.setVisible(false);
            myHome.setVisible(true);
            
        //If the add Material button was selected    
        } else if( theEvent.getSource() == myMatAdd) {
        	materialAddButtonAction();

    	//If the task add button was selected	
    	}else if( theEvent.getSource() == myTaskAdd) {
    		taskAddButtonAction();
    		
    	//If the delete button was selected	
    	}else if(theEvent.getSource() == myDeleteButton) {
    		deleteButtonAction();
    		
    	//If the save button was selected	
    	}else if(theEvent.getSource() == mySaveButton) {
    		saveButtonAction();
    	}
    }

    /**
     * Method to be called when the add material button is selected
     * @author Miranda Bessex 6/6/19
     */
    private void materialAddButtonAction() {
    	MaterialPanel addMat = new MaterialPanel("", "", "");
		Materials newMat = addMat.returnMat();
		if(newMat.getCost() > 0) {
			myProject.addMaterials(newMat);

			String matInfo = "	Name: " + newMat.getName() + "       Cost: " + 
						Double.toString(newMat.getCost()) + "       Quantity: " + 
						Integer.toString(newMat.getQuantity());
			
			MaterialButton newLab = new MaterialButton(newMat, matInfo);
			newLab.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					MaterialEditPanel addMat = new MaterialEditPanel(newLab.getMaterials().getName(), Double.toString(newLab.getMaterials().getCost()), Integer.toString(newLab.getMaterials().getQuantity()), myProjectPage);
					Materials newMaterial = addMat.returnMat();
					
					if(!newMaterial.getName().equals("invalid")) {
						
						deleteMaterial(newMaterial);
						addMaterial(newMaterial);
						
						String matInfo = "	Name: " + newMaterial.getName() + "       Cost: " + 
								Double.toString(newMaterial.getCost()) + "       Quantity: " + 
								Integer.toString(newMaterial.getQuantity());
						
						newLab.setMaterial(newMaterial);
						newLab.setText(matInfo);
					}else{
						myMatPan.remove(newLab);
					}
					
					
				
					myMatPan.repaint();
					myMatPan.revalidate();
				}
				
			});
			
			myMatPan.add(newLab);
			myMatPan.repaint();
			myMatPan.revalidate();
		}
		
	}

	/**
     * method to be called when the add task button has been clicked
     * @author Miranda Bessex 6/6/19
     */
    private void taskAddButtonAction() {
    	TaskPanel addTask = new TaskPanel("description", false);
		Tasks newTask = addTask.returnTask();   		
		myProject.addTasks(newTask);
		
		String taskInfo = newTask.getName();
		TaskButton newTaskButton = new TaskButton(newTask, taskInfo);
		
		newTaskButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskEditPanel addTask = new TaskEditPanel(newTaskButton.getTask().getName(), newTaskButton.getTask().isCompleted(), myProjectPage);
				
				Tasks newTasks = addTask.returnTask();
				
				if(!newTasks.getName().equals("No Name Given")) {
					
					deleteTask(newTaskButton.getTask());
					
					String TaskInfo2;
					if(newTasks.isCompleted()) {
						TaskInfo2 = "	Name: " + newTasks.getName() + "    Completed: Yes";
					}else {
						TaskInfo2 = "	Name: " + newTasks.getName() + "    Completed: No";
					}
					
					newTaskButton.setTask(newTasks);
					newTaskButton.setText(TaskInfo2);
				}else{
					myTaskPan.remove(newTaskButton);
				}
				
				myTaskPan.repaint();
				myTaskPan.revalidate();
			}
			
		});
		
		myTaskPan.add(newTaskButton);
		myTaskPan.repaint();
		myTaskPan.revalidate();
		
	}

	/**
     * method to be called when the delete button has been clicked
     * @author Miranda Bessex 6/7/19
     */
    private void deleteButtonAction() {
    	int areYouSure = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this project?", "Delete", JOptionPane.YES_NO_OPTION);
		if(areYouSure == JOptionPane.YES_OPTION) {
			myApp.deleteProject(myProject.getProjectID());
			myHome.setUpList(myApp);
			this.setVisible(false);
            myHome.setVisible(true);
		}
	}

	/**
     * method to be called when the Save button has been clicked
     * @author Miranda Bessex 6/7/19
     */
	private void saveButtonAction() {
		myProject.setProjectName(myName.getText());
		myApp.addProject(myProject);
		myHome.setUpList(myApp);
		this.setVisible(false);
        myHome.setVisible(true);
		
	}
	
	/**
	 * Method to add materials to the material list
	 * @author Miranda Bessex 6/01/19
	 */
	void addMaterial(Materials theMaterial) {
		myProject.addMaterials(theMaterial);
	}
	
	/**
	 * Method to delete materials from the material list
	 * Deletes material by name so once the name has been added it cannot be change
	 * @author Miranda Bessex 6/01/19
	 */
	void deleteMaterial(Materials theMaterial) {
		myProject.addMaterials(theMaterial);
		for (Iterator<Materials> iterator = (myProject.getMaterials()).iterator(); iterator.hasNext(); ) {
			Materials value = iterator.next();
			if(value.getName().equals(theMaterial.getName())) {
				iterator.remove();
			}
		}
	}

	
	/**
	 * Method to delete task from the task list
	 * Deletes task by name so once the name has been added it cannot be change
	 * @author Miranda Bessex 6/04/19
	 */
	void deleteTask(Tasks theTask) {
		myProject.addTasks(theTask);
		for (Iterator<Tasks> iterator = (myProject.getTasks()).iterator(); iterator.hasNext(); ) {
			Tasks value = iterator.next();
			if(value.getName().equals(theTask.getName())) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * Method to add Tasks to the task list
	 * @author Miranda Bessex 6/01/19
	 */
	void addTasks(Tasks theTask) {
		myProject.addTasks(theTask);
	}
}
