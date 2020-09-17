package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Materials;

public class MaterialPanel extends JOptionPane{


		/**
		 * 
		 */
		private String myCost;
		private String myName;
		private String myAmount;
		/**
		 * @author Joseph Rushford 5/24/19
		 */
		public MaterialPanel(String theName, String theCost, String theQuantity) {
			JTextField cost = new JTextField(30);
			JTextField name = new JTextField(30);
			JTextField amount = new JTextField(30);
			name.setText(theName);
			cost.setText(theCost);
			amount.setText(theQuantity);
			JPanel namePan = new JPanel();
			JPanel costPan = new JPanel();
			JPanel amountPan = new JPanel(); 
			namePan.add(new JLabel("Name:"), BorderLayout.WEST);
			namePan.add(name, BorderLayout.EAST);
			costPan.add(new JLabel("Cost:"), BorderLayout.WEST);
			costPan.add(cost, BorderLayout.EAST);
			amountPan.add(new JLabel("Quantity:"), BorderLayout.WEST);
			amountPan.add(amount, BorderLayout.EAST);
			JPanel[] settings = new JPanel[3];
			name.setName("Name");
			settings[1] = costPan;
			settings[0] = namePan;
			settings[2] = amountPan;
			int confirm = JOptionPane.showConfirmDialog(null, settings, "New Material",  JOptionPane.OK_CANCEL_OPTION);
			if(confirm == JOptionPane.OK_OPTION) {
				myCost = cost.getText();
				myName = name.getText();
				myAmount = amount.getText();
			} else {
				myCost = "invalid input";
				myName = "No Name Given";
				myAmount = "invalid input";
			}
		
		}
		/**
		 * @author Joseph Rushford 5/24/19
		 */
		public Materials returnMat() {
			Materials newMat = new Materials("invalid", (double) -1, 0);
			try {
				newMat = new Materials(myName, Double.parseDouble(myCost), Integer.parseInt(myAmount));
			} catch(NumberFormatException e) {
				
			}
			return newMat;
		}

	  }

