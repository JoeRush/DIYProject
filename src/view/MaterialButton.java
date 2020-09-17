package view;

import javax.swing.JButton;

import model.Materials;

/**
 * A class to model a material button, a JBUtton with access to the material it was created with
 * @author Miranda Bessex
 */
public class MaterialButton extends JButton {

	private Materials myMaterial;
	
	/**
	 * Constructor for the Material Button
	 * @param theProject
	 * @author Miranda Bessex
	 */
	public MaterialButton(Materials theMaterial, String theString) {
		super(theString);
		myMaterial = theMaterial;
	
	}
	
	/**
	 * A getter for the material on the button
	 * @author Miranda Bessex 6/6/19
	 */
	public Materials getMaterials() {
		return myMaterial;
	}
	
	/**
	 * @author Miranda Bessex 6/6/19
	 * Setter for the material
	 */
	public void setMaterial(Materials theMaterial) {
		myMaterial = theMaterial;
	}
}
