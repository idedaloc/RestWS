package com.example.demo.versioning;

public class Name {
	
	private String firstname;
	private String lastname;
	
	/**
	 * @param firstname
	 * @param lastname
	 */
	public Name() {

	}
	
	/**
	 * @param firstname
	 * @param lastname
	 */
	public Name(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	

}
