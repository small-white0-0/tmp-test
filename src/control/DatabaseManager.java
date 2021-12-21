package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ComboBoxModel;

public class DatabaseManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // Load the JDBC driver
		try {
			Class.forName("org.mariadb.jdbc");
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
	    System.out.println("Driver loaded");

	    Connection connection = null;
	    // Try to connect
	    try {
	    	connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "tt", "pwd123");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    
	    System.out.println("It works!");

	    try {
	    	connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
	}

	public static String [] getClassesName() {
		// TODO Auto-generated method stub
		return new String[] {"chinese","english"};
	}
}
