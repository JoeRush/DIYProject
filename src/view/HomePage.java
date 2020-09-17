/* 
 * TCSS 360
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Application.Application;
import model.Project;
import model.User;

/**
 * Class to start splash screen for DIY project.
 * Represents the home screen of the application when first opened up. 
 * 
 * @author gehry guest 5/13/19
 * @author joseph rushford 5/24/19
 * @author Miranda Bessex 6/3/19
 * @version 1.7.44
 */

public class HomePage extends JFrame implements ActionListener {
    
    /** A number for Serialization. */
    private static final long serialVersionUID = -2102839873375270630L;
                    
    /** The width of the panel. */
    private static final int WIDTH = 600;

    /** The height of the panel. */
    private static final int HEIGHT = 700;
    
    /** The application reference */
    private Application myApp;
    
    /** The current User of the application */
    private User myUser;
     
    /** To bring up directory for user. */
    private JFileChooser myOpenFile;
    
    /** The button for the more tab. */
    private JButton myAddButton;
    
    /** menu bar for GUI. */
    private JMenuBar myMenuBar;
    
    /** The button for the JMenu tab. */
    private JMenu myFileShareMenu;
    
    /** The button for the Other tab. */
    private JMenu myOtherMenu;
    
    /** button for pop up to talk about version.*/
    private JMenuItem myAboutButton;
    
    /** The button for importing files. */
    private JMenuItem myImportButton;
    
    /** The button for exporting items. */
    private JMenuItem myExportButton;
    
    /** Button for confirming the users settings. */
    private JMenuItem mySettings;
    
    /** stores the information of last opened file. */
    private File myLocation;
    
    private File myLoadedCSV;
    
    /** Creates list for projects. */
    private ArrayList<JButton> myProjectButtons;
    
    /** The main home panel */
    private JPanel myMainHomePanel;
    
    /** The reference to the home page */
    private HomePage myHomePage;

    
    
    /**
     * Constructor for the HomePage
     * @author gehry guest 5/13/19
     * @author Miranda Bessex 6/6/19
     */
    public HomePage() {
    	
    	userLogIn();
    	
    	myApp = new Application(myUser);
    	
    	initializeFields();
        
        myHomePage = this;
        
        setUp();
    }
    
    /**
     * Method to initialize fields
     * @author Miranda Bessex 6/6/19
     */
    private void initializeFields() {
    	
    	myOpenFile = new JFileChooser();
        
        myLocation = new File("project_files");
        
        myAddButton = new JButton("Add");
        
        myMenuBar = new JMenuBar();
        
        myFileShareMenu = new JMenu("FileSharing...");
        
        myOtherMenu = new JMenu("Options...");

        myImportButton = new JMenuItem("Import");
        
        myExportButton = new JMenuItem("Export");
        
        myAboutButton = new JMenuItem("About");
        
        mySettings = new JMenuItem("Settings");
        
        myMainHomePanel = new JPanel();
        
        myProjectButtons = new ArrayList<JButton>();
        
        myLoadedCSV = new File("Temp");
	}
    

    /**
     * A method to run a option pane for log in information and creates a user
     * @author Miranda Bessex 6/8/19
     */
	private void userLogIn() {
		
    	String name = JOptionPane.showInputDialog("Enter Username: ");
    	String email = JOptionPane.showInputDialog("Enter Email: ");
    	myUser = new User(name, email);
    }
    
    /**
     * sets up containers, JPanels, and progress bar with layouts and colors.
     * @author gehry guest 5/13/19
     */
    private void setUp() {
        
        final Container container = getContentPane();
        
        setUpList(myApp);
        
        setUpMenuBar();
        
        addActionListenersToButtons();
        
        container.add(myMainHomePanel, BorderLayout.CENTER);
        
        displayHome();
    }
    
    /**
     * This is where we populate the list that appears on the GUI.
     * @author Miranda Bessex 6/7/19
     */
    void setUpList(Application myApp) {
    	
    	//Clear out old list
    	myProjectButtons.clear();
             
    	//create new list
        ArrayList<Project> projectsList = myApp.getProjectList();
        
        //If the list is empty display the opening add new project button
        if(projectsList.isEmpty()) {
        	myProjectButtons.add(createBeginingAddButton());
        }
        
        //create a button for each of the projects in the list
        for(Project p: projectsList) {
        	
        	//Uses the toString method in the project class to know what to display
        	ProjectButton button = new ProjectButton(p);
        	
        	//button formatting
        	button.setContentAreaFilled(false);
        	button.setHorizontalAlignment(SwingConstants.LEFT);
        	button.setBorderPainted(true);
        	button.setBackground(Color.LIGHT_GRAY);
        	button.setOpaque(true);
        	button.setPreferredSize(new Dimension(500, 200));
        	button.setFont(new Font("Arial", Font.BOLD, 20));
        	
        	//button action listener
        	button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					myHomePage.setVisible(false);
					new ProjectPage(myHomePage, myApp, button.getProject());
				}
        	});
        	myProjectButtons.add(button);
        }  
        setUpMainPanel();
    }
    
    /**
     * Method to create an "Add Project button" within the main list if no projects exist for the user
     * Takes you to the add project page
     * @return Add first project button
     * @author Miranda Bessex 6/7/19
     */
    private JButton createBeginingAddButton() {
    	JButton button = new JButton("+ Add First Project!");
    	button.setContentAreaFilled(false);
    	button.setHorizontalAlignment(SwingConstants.LEFT);
    	button.setBorderPainted(true);
    	button.setBackground(Color.LIGHT_GRAY);
    	button.setOpaque(true);
    	button.setPreferredSize(new Dimension(500, 200));
    	button.setFont(new Font("Arial", Font.BOLD, 20));
    	
    	button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myHomePage.setVisible(false);
				new AddPage(myHomePage, myApp, myLoadedCSV);
			}
    	});
		return button;
	}

	/**
     * populates the menu bar with actions,buttons and menus.
     * @author gehry guest 5/13/19
     */
    private void setUpMenuBar() {
        
        //height of buttons
        final int width = 100;
        final int height = 20;
        final String buffer = " | ";
        
        //create spacers and make them not selectable.
        final JMenu spacer1 = new JMenu(buffer);
        final JMenu spacer2 = new JMenu(buffer);
        final JMenu spacer3 = new JMenu(buffer);
        final JMenu spacer4 = new JMenu(buffer);
        final JMenu spacer5 = new JMenu(buffer);
        
        spacer1.setEnabled(false);
        spacer2.setEnabled(false);
        spacer3.setEnabled(false);
        spacer4.setEnabled(false);
        spacer5.setEnabled(false);
        
        //to make buttons look like Jmenu items.
        myAddButton.setOpaque(true);
        myAddButton.setContentAreaFilled(false);
        myAddButton.setBorderPainted(false);
        myAddButton.setFocusable(false);
       
        customizeJMenuButtons();

        //Set the sizes of the buttons.
        myAddButton.setPreferredSize(new Dimension(width, height));
        myFileShareMenu.setPreferredSize(new Dimension(width, height));
        myOtherMenu.setPreferredSize(new Dimension(width, height));
        myMenuBar.setPreferredSize(new Dimension(WIDTH, 30));
        
        //Add items to JMenuBar.
        myMenuBar.add(spacer5);
        myMenuBar.add(myAddButton);
        myMenuBar.add(spacer2);
        
        //Add items to FileShare SubMenu
        myMenuBar.add(myFileShareMenu);
        myFileShareMenu.add(myImportButton);
        myFileShareMenu.addSeparator();
        myFileShareMenu.add(myExportButton);
        myMenuBar.add(spacer3);
        
        //Add Items to OtherMenu SubMenu.
        myOtherMenu.add(myAboutButton);
        myOtherMenu.addSeparator();
        myOtherMenu.add(mySettings);
        myMenuBar.add(myOtherMenu);
        myMenuBar.add(spacer4);
        
        //sets the layout through gridbag layout.
        myMenuBar.setLayout(new GridBagLayout());        
    }
    
    /**
     * changes the look of the buttons to appear and act similar to jmenus.
     * @author gehry guest 5/13/19
     */
    private void customizeJMenuButtons() {
        
        myAddButton.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theE) {
                final ButtonModel model = (ButtonModel) theE.getSource();

                if (model.isRollover()) {
                    myAddButton.setBackground(new Color(163,184,204)); 
                    
                    myAddButton.setOpaque(true);
                } else {
                    myAddButton.setBackground(null);
                    
                    myAddButton.setOpaque(false);
                }
            }
        });
    }
   

    /**
     * creates the main home panel that holds the home view
     * This is created each time the list of projects is updated
     * @author gehry guest 5/13/19
     * @author Miranda Bessex 6/8/19
     */
    private void setUpMainPanel() {
    	
    	myMainHomePanel.removeAll();
    		
    	final JLabel imgLabel = new JLabel("",
                new ImageIcon(getClass().getClassLoader().getResource("App Logo_without name.png")), 
                SwingConstants.CENTER);
        
    	myMainHomePanel.add(myMenuBar, BorderLayout.NORTH);
        
        JPanel projectPanel = new JPanel();
        projectPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        projectPanel.setLayout(new GridLayout(10, 1));
        projectPanel.add(imgLabel);
        
        for(JButton b: myProjectButtons) {
        	projectPanel.add(b);
        }
       
        myMainHomePanel.add(new JScrollPane(projectPanel), BorderLayout.CENTER);
        
        myMainHomePanel.repaint();
        myMainHomePanel.revalidate();
    }
    
    
   /**
    * Method to add action listeners to all of the hard coded buttons
    * @author Miranda Bessex 6/10/19
    */
    private void addActionListenersToButtons() {
        
        myAddButton.addActionListener(this);
        
        myExportButton.addActionListener(this);
        
        myImportButton.addActionListener(this);
        
        myAboutButton.addActionListener(this);
       
        mySettings.addActionListener(this);
        
        myFileShareMenu.addActionListener(this);
    }
    
    
    /**
     * Sets up size, location, visibility for frame of the home page.
     * @author gehry guest 5/13/19
     */
    private void displayHome() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    
        this.pack();
    
        this.setLocationRelativeTo(null);
    
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        this.setResizable(false);
    
        this.setVisible(true);
    }    
    

    /**
     * Method to open a file when you want to import data into the application
     * @author joseph rushford 5/24/19
     * @author Miranda Bessex 6/07/19
     */
    private void openFile() {
    
        myOpenFile.setCurrentDirectory(myLocation);

        final int returnValue = myOpenFile.showOpenDialog(null); 

        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                
                myLocation = myOpenFile.getCurrentDirectory();
                
                final Scanner file = new Scanner(myOpenFile.getSelectedFile());
                
                if (file.hasNext()) {
                    
                    myUser.setUserEmail(file.next());
                }
                if (file.hasNext()) {
                	
                	myUser.setUserName(file.next());
                }
                
                myApp.loadAllEntries(myOpenFile.getSelectedFile());
                myApp.setFile(myOpenFile.getSelectedFile());
                myLoadedCSV = myOpenFile.getSelectedFile();
                setUpList(myApp);
               
            } catch (final FileNotFoundException e) {
                
                JOptionPane.showMessageDialog(null, "File Not Found"); 
            }
        }
    }
    
    
    /**
     * Method to save the file on export
     * @author joseph rushford 5/24/19
     */
    private void saveFile() {
        
        myOpenFile.setCurrentDirectory(myLocation);
        
        final int returnValue = myOpenFile.showSaveDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	
        	myApp.setFile(myOpenFile.getSelectedFile());
        	
        	myLoadedCSV = myOpenFile.getSelectedFile();
        	
        	myApp.write();
        }
    }
   
    /**
     * All Actions perfomed by all of the buttons on the page
     * @author Miranda Bessex 6/8/19
     * @author joseph rushford 5/24/19
     * @author gehry guest 5/13/19
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
        if (theEvent.getSource() == myAddButton) {
            this.setVisible(false);
            new AddPage(this, myApp, myLoadedCSV);
          
        } else if (theEvent.getSource() == myAboutButton) {
            new AboutMe();
            
        } else if (theEvent.getSource() == myImportButton) {
            openFile();
            
        } else if (theEvent.getSource() == myExportButton) {
            saveFile();
            
        } else if (theEvent.getSource() == mySettings) {
            final SettingPopUp set = new SettingPopUp(myUser.getUserEmail(), myUser.getUserName());
            myUser.setUserEmail(set.getEmail());
            myUser.setUserName(set.getName()); 
        }
    }
}
