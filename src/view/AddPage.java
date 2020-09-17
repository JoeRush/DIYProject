package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class AddPage extends JFrame implements ChangeListener, ActionListener {
	
    /**
     * This class represents the page in the gui for adding a new project to the project set
     * @author Joseph Rushford 5/20/19
     * @author Gehry Guest 5/26/19
     * @author Miranda Bessex 6/01/19
     */
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
    
    /**The confirmation button */
    private JButton myConfirmButton;
    
    /** The cancel button */
    private JButton myCancelButton;
    
    /** The persistent file that the added project will be updated to */
    private File myCSVFile;
    
    /** The name of the project */
    private JTextField myName;
    
    /** List of materials */
    private ArrayList<Materials> myMaterials;
    
    /** list of tasks */
    private ArrayList<Tasks> myTasks;
    
    /** reference to the project being built */
    private Project myProject;
    
    /** Reference to the home page */
    private HomePage myHome;
    
    /** The tab object that holds the materials and tasks */
    private JTabbedPane myTab;
    
    
//    private JScrollPane myMats;
//    private JScrollPane myTsks;
    
    /** The material display panel */
    private JPanel myMaterialPanel;
    
    /** Button to add Tasks */
	private JButton myTaskAdd;

	/** Button to add Materials */
	private JButton myMatAdd;
	
	/**Reference to the application model */
    private Application myApp;

    /** The tasks display panel */
	private JPanel myTaskPanel;
	
	/** Reference to the add page */
	private AddPage myAddPage;
	
	
	/**
	 * Constructor for the add page
	 * @param theHome page
	 * @param theApp controller
	 * @param theCSV file with user information
	 * @author Gehry Guest 5/26/19
	 * @author Joseph Rushford 5/24/19
	 * @author Miranda Bessex 6/8/19
	 */
    public AddPage(HomePage theHome, Application theApp, File theCSV) {
    	myApp = theApp;
        myHome = theHome;
        myCSVFile = theCSV;
        
    	initializeFields();
    	setUpFrame();
    }
    
    /**
     * Method to initialize all of the fields in the constructor
     * @author Miranda Bessex 6/02/19
     * @author Joseph Rushford 5/24/19
     */
    private void initializeFields() {
    	
    	myAddPage = this;
    	
    	myMaterialPanel = new JPanel();
        myTaskPanel = new JPanel();
        
        myTaskPanel.setLayout(new GridLayout(50, 1));
        myMaterialPanel.setLayout(new GridLayout(50, 1));
        
        myTab = new JTabbedPane();
        
        myName = new JTextField(20);
        
        myMatAdd = new JButton("Add Materials");
		myTaskAdd = new JButton("Add Tasks");
	
		myConfirmButton = new JButton("Confirm");
		myCancelButton = new JButton("Cancel");
	}


	/**
     * Method to set up and format the frame
     * @author Miranda Bessex 6/01/19
     * @author Joseph Rushford 5/24/19
     */
    private void setUpFrame() {
    	
    	//Set up container and main panel
    	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    	final Container container = getContentPane();
   
    	final JPanel panel = new JPanel(new GridLayout(4, 1));
    	container.add(panel, BorderLayout.CENTER);
    	panel.setOpaque(true);
    	
    	//Created Header image at the top of the panel
    	final JLabel imgLabel = new JLabel("",
                new ImageIcon(getClass().getClassLoader().getResource("App Logo_without name.png")), 
                SwingConstants.CENTER); 
    	
    	panel.add(imgLabel);
    	
    	//Create the name panel at the top of the page
    	JPanel namePan = new JPanel();
        namePan.add(new JLabel("Project Name:"));
        namePan.add(myName);
        panel.add(namePan);
        
        //Create a panel with all the add buttons for material and tasks
        JPanel addStuffButtons = new JPanel();
        
        myMatAdd.addActionListener(this);
        addStuffButtons.add(myMatAdd, BorderLayout.WEST);

        myTaskAdd.addActionListener(this);
        addStuffButtons.add(myTaskAdd, BorderLayout.EAST);
        
        panel.add(addStuffButtons);
        
        myConfirmButton.addActionListener(this);
		myCancelButton.addActionListener(this);

    	createSliders();
    	createTabPanes();
    	
    	//Panel to hold both sliders
    	JPanel sliderPanel = new JPanel(new BorderLayout());
    	
    	//Difficulty slider
    	JPanel diffPan = new JPanel();
    	diffPan.add(new JLabel("Difficulty"));
    	diffPan.add(myDifficultySlider);
    	sliderPanel.add(diffPan, BorderLayout.WEST);
    	
    	//Priority slider
    	JPanel priorPan = new JPanel();
    	priorPan.add(new JLabel("Priorty"));
    	priorPan.add(myPriortySlider);
    	sliderPanel.add(priorPan, BorderLayout.EAST);
    	
    	//Add sliders to the main panel
    	panel.add(sliderPanel);
    	
    	//Add the material and task buttons to the main panel
        panel.add(addStuffButtons);
         
        final JPanel buttonAndTabPanel = new JPanel(new BorderLayout());
        
        final JPanel buttonOptions = new JPanel();
         
        buttonOptions.add(myConfirmButton);
        buttonOptions.add(myCancelButton);
        
        buttonAndTabPanel.add(myTab, BorderLayout.NORTH);
        buttonAndTabPanel.add(buttonOptions, BorderLayout.SOUTH);
       
        container.add(buttonAndTabPanel, BorderLayout.SOUTH);
         
        this.pack();

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

        this.setVisible(true);
	}

	/**
     * creates the slider for the thickness menu.
     * @author Joseph Rushford 5/23/19
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
	 * Method to construct the tabbed pane for materials and tasks
	 * @author Joseph Rushford 5/26/19
	 * @author Miranda Bessex 6/01/19
	 */
    public void createTabPanes() {
    	JScrollPane myMats = new JScrollPane(myMaterialPanel);
    	JScrollPane myTsks = new JScrollPane(myTaskPanel);
    	
    	myTab.setPreferredSize(new Dimension(500, 250));
    	
    	myTab.add("Materials", myMats);
    	myTab.add("Tasks", myTsks);
    	
    	myMaterials = new ArrayList<Materials>();
    	myTasks = new ArrayList<Tasks>();
    }

   /**
    * sets the integer value for difficulty.
    * @author Gehry Guest 5/20/19
    * @param theValue integer for difficulty.
    */
    public void setMyDifficultyValue(final int theValue) {
        myDifficultyValue = theValue;
    }
       
   /**
    * returns a integer value.
    * @author Gehry Guest 5/20/19
    * @return myDifficultyValue integer from slider.
    */
    public int getMyDifficultyValue() {
        return myDifficultyValue;
    }
    /**
     * sets the integer value for thickness.
	 * @author Joseph Rushford 5/26/19
     * @param thePriortyValue integer for Proirty.
     */
     public void setMyPriortyValue(final int theValue) {
         myDifficultyValue = theValue;
     }
        
    /**
     * returns a integer value.
	 * @author Joseph Rushford 5/26/19
     * @return Value integer from Priortyslider.
     */
     public int getMyPriortyValue() {
         return myPriortyValue;
     }

    /**
     * @author Gehry Guest 5/20/19
     * @author Joseph Rushford 5/26/19
     */
    @Override
    public void stateChanged(final ChangeEvent theEvent) {
    	if(theEvent.getSource() == myDifficultySlider) {
    		setMyDifficultyValue(myDifficultySlider.getValue());
    	} else if( theEvent.getSource() == myPriortySlider) {
    		setMyPriortyValue(myPriortySlider.getValue());
    	}
    }
	/**
	 * Monitors and redirects to different pages for all of the actions availible
	 * @author Miranda Bessex 6/02/19
	 * @author Joseph Rushford 5/27/19
	 */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        //Work in progress Needs the mainpage/filemanager done actually add
        if (theEvent.getSource() == myConfirmButton) {
        	
        	myApp.setFile(myCSVFile);
        	
        	//Create a new project and add that project to the application
            myApp.addProject(new Project(myName.getText(), myDifficultyValue, myPriortyValue, myTasks, myMaterials));	
        	
            //reset the list on the home page
            myHome.setUpList(myApp);
        	
            //return to home page
            this.setVisible(false);
            myHome.setVisible(true);
            
            
        } else if (theEvent.getSource() == myCancelButton) {
            this.setVisible(false);
            myHome.setVisible(true);
        } else if( theEvent.getSource() == myMatAdd) {
        	materialAddButtonAction();

    	}else if( theEvent.getSource() == myTaskAdd) {
    		taskAddButtonAction();
    		
    	}
    }

    /**
     * Method to be called when the add material button is clicked
     * Creates Material Buttons for all materials created
     * To edit or delete these materials in the project you can do it by clicking on them
     * @author Miranda Bessex 6/01/19
     * @author Joseph Rushford 5/27/19
     */
    private void materialAddButtonAction() {
    	MaterialPanel addMat = new MaterialPanel("", "", "");
		Materials newMat = addMat.returnMat();
		if(newMat.getCost() > 0) {
			myMaterials.add(newMat);

			String matInfo = "	Name: " + newMat.getName() + "       Cost: " + 
						Double.toString(newMat.getCost()) + "       Quantity: " + 
						Integer.toString(newMat.getQuantity());
			
			MaterialButton newLab = new MaterialButton(newMat, matInfo);
			newLab.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					MaterialEditPanel addMat = new MaterialEditPanel(newLab.getMaterials().getName(), Double.toString(newLab.getMaterials().getCost()), Integer.toString(newLab.getMaterials().getQuantity()), myAddPage);
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
						myMaterialPanel.remove(newLab);
					}
					
					
				
					myMaterialPanel.repaint();
					myMaterialPanel.revalidate();
				}
				
			});
			
			
			myMaterialPanel.add(newLab);
			myMaterialPanel.repaint();
			myMaterialPanel.revalidate();
		}
	}

    /**
     * Method for deleting the material before saving the editited version
     * @param materials
     */
	protected void deleteOrigninalMat(Materials materials) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Method to be called when the add task button is clicked
     * @author Miranda Bessex 6/06/19
     */
	private void taskAddButtonAction() {
		TaskPanel addTask = new TaskPanel("", false);
		Tasks newTask = addTask.returnTask();
		myTasks.add(newTask);
		
		String TaskInfo;
		if(newTask.isCompleted()) {
			TaskInfo = "	Name: " + newTask.getName() + "    Completed: Yes";
		}else {
			TaskInfo = "	Name: " + newTask.getName() + "    Completed: No";
		}
		
		TaskButton newTaskButton = new TaskButton(newTask, TaskInfo);
		newTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskEditPanel addTask = new TaskEditPanel(newTaskButton.getTask().getName(), newTaskButton.getTask().isCompleted(), myAddPage);
				
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
					myTaskPanel.remove(newTaskButton);
				}
				
				myTaskPanel.repaint();
				myTaskPanel.revalidate();
			}
			
		});
	
		
		myTaskPanel.add(newTaskButton);
		myMaterialPanel.repaint();
		myTaskPanel.revalidate();
		
		
	}
	
	/**
	 * Method to add materials to the material list
	 * @author Miranda Bessex 6/01/19
	 */
	void addMaterial(Materials theMaterial) {
		myMaterials.add(theMaterial);
	}
	
	/**
	 * Method to delete materials from the material list
	 * Deletes material by name so once the name has been added it cannot be change
	 * @author Miranda Bessex 6/01/19
	 */
	void deleteMaterial(Materials theMaterial) {
		myMaterials.add(theMaterial);
		for (Iterator<Materials> iterator = myMaterials.iterator(); iterator.hasNext(); ) {
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
		myTasks.add(theTask);
		for (Iterator<Tasks> iterator = myTasks.iterator(); iterator.hasNext(); ) {
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
		myTasks.add(theTask);
	}
}

