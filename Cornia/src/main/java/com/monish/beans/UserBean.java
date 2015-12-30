package com.monish.beans;

public class UserBean {

	private String uname ;
	private String pass ;
	private String firstName;
	private String lastName; 
	public boolean valid; 
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}


	@Override
	public String toString() {
		return "UserBean [uname=" + uname + ", pass=" + pass + "]";
	}
}
