/**
 * 
 */
package model;


/**
 * @author Annora Jones 5/28/19
 *
 */
public class Materials {

	/**
	 * The name of the Material.
	 * @author Annora Jones 5/28/19
	 */
	private String name;
	
	/**
	 * The cost of one unit of this Material.
	 * @author Annora Jones 5/28/19
	 */
	private double cost;
	
	/**
	 * The number of units of this material the user needs.
	 * @author Annora Jones 5/28/19
	 */
	private int quantity;
	
	/**
	 * The total cost of all of this kind of material.
	 * @author Annora Jones 5/28/19
	 */
	private double totalCost;
	
	/**
	 * @author Annora Jones 5/28/19
	 * Constructs a Material object.
	 * @param theName the name of the Material.
	 * @param theCost the cost of one unit of this material.
	 * @param theQuantity the number of units the user needs.
	 */
	public Materials(final String theName, final double theCost, final int theQuantity){
		
		name = theName;
		cost = theCost;
		quantity = theQuantity;
		totalCost = sumCosts();
	}
	
	
	//Accessor methods
	
	/**
	 * @author Annora Jones 5/28/19
	 * Calculates the total cost of this type of Material the user requires.
	 * i.e. cost * quantity
	 */
	private double sumCosts() {
		
		return cost * quantity;
	}
	
	/**
	 * @author Annora Jones 5/28/19
	 * @return the name of the Material
	 */
	public String getName() {
	
		return name;
	}
	
	/**
	 * @author Annora Jones 5/28/19
	 * @return the cost of the Material
	 */
	public double getCost() {
		
		return cost;
	}
	
	/**
	 * @author Annora Jones 5/28/19
	 * @return the quantity of the Material
	 */
	public int getQuantity() {
		
		return quantity;
	}
	
	/**
	 * @author Annora Jones 5/28/19
	 * @return the total cost of the selected quantity of Materials
	 */
	public double getTotalCost() {
		
		return totalCost;
	}
	
	
	//Mutator methods
	
	/**
	 * @author Annora Jones 5/28/19
	 * @param theName the new name to give to the Material
	 */
	public void setName(final String theName) {
		
		name = theName;
	}
	
	/**
	 * @author Annora Jones 5/28/19
	 * @param theCost the new cost to give to the Material
	 */
	public void setCost(final double theCost) {
		
		cost = theCost;
	}
	
	/**
	 * @author Annora Jones 5/28/19
	 * @param theQuantity the new quantity of the Material.
	 */
	public void setQuantity(final int theQuantity) {
		
		quantity = theQuantity;
	}
}
