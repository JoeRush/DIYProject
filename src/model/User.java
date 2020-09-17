package model;

/**
 * Class to model a User of the APP
 * @author Miranda Bessex 6/10/19
 *
 */

public class User {

	/** User Name */
	private String myName;
	
	/** User Email */
	private String myEmail;
	
	
	/**
	 * Constructor for the User
	 * @param theName
	 * @param theEmail
	 * @author Miranda Bessex 6/10/19
	 */
	public User(String theName, String theEmail) {
		myName = theName;
		myEmail = theEmail;
	}
	
	/**
	 * Getter for the name
	 * @return name
	 * @author Miranda Bessex 6/10/19
	 */
	public String getUserName() {
		return myName;
	}
	
	/**
	 * Getter for the email
	 * @return email
	 * @author Miranda Bessex 6/10/19
	 */
	public String getUserEmail() {
		return myEmail;
	}
	
	/**
	 * Setter for User Name
	 * @author Miranda Bessex 6/10/19
	 */
	public void setUserName(String theName) {
		myName = theName;
	}
	
	/**
	 * Setter for Email
	 * @author Miranda Bessex 6/10/19
	 */
	public void setUserEmail(String theEmail) {
		myEmail = theEmail;
	}
	
}
