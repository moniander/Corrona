package com.monish.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.monish.beans.UserBean;

public class UserDao {

	static Connection currentCon = null;
	static ResultSet rs = null;

	public static UserBean login(UserBean user) {

		// preparing some objects for connection
		Statement stmt = null;
		String username = user.getUname();
		String password = user.getPass();
		String searchQuery = "select * from User where firstname='" + username + "' AND lastname='" + password + "'"; 
		
		String opuser = "adminaGbt96t";
	    String oppassword = "j3VK_26wrz_L";
	    String host = "jdbc:mysql://" + System.getenv().get("OPENSHIFT_MYSQL_DB_HOST") + ":"
				+ System.getenv().get("OPENSHIFT_MYSQL_DB_PORT") + "/Dragon";
		
		
		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + searchQuery);

		try { // connect to DB

			Class.forName("com.mysql.jdbc.Driver");
			currentCon = DriverManager.getConnection(host,opuser,oppassword);
					
			System.out.println("connected");
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next(); // if user does not exist set the isValid
										// variable to false
			if (!more) {
				System.out.println("Sorry, you are not a registered user! Please sign up first");
				user.setValid(false);
			} // if user exists set the isValid variable to true
			else if (more) {

				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				System.out.println("Welcome " + firstName);

				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setValid(true);
			}

		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} // some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return user;
	}
}
