package view;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Materials;
import model.Tasks;

public class TaskPanel extends JOptionPane{
		private boolean myState;
		private String myName;

		/**
		 * @author Joseph Rushford 5/25/19
		 * @param Description of the task.
		 * @param State of completion of the task.
		 */
		public TaskPanel(String Description, boolean State) {

			JTextField name = new JTextField(30);
			JCheckBox status = new JCheckBox("Completed");
			JPanel namePan = new JPanel();
			JPanel statusPan = new JPanel();
			statusPan.add(status);
			namePan.add(new JLabel("Name:"), BorderLayout.WEST);
			namePan.add(name, BorderLayout.EAST);
			
			JPanel[] settings = new JPanel[2];
			name.setName("Name");
			
			settings[0] = namePan;
			settings[1] = statusPan;
			int confirm = JOptionPane.showConfirmDialog(null, settings, "New Task",  JOptionPane.OK_CANCEL_OPTION);
			
			if(confirm == JOptionPane.OK_OPTION) {
				myState = status.isSelected();
				myName = name.getText();
			} else {
				myName = "No Name Given";
				myState = false;
			}
		
		}
		/**
		 * @author Joseph Rushford 5/25/19
		 */
		public Tasks returnTask() {
			Tasks newTask = new Tasks(myName, myState);

			return newTask;
		}

	  }
