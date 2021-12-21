package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SqlOperation {

	private Connection connection;
	
	private void sqlConnetct() {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据库驱动加载失败");
			e.printStackTrace();
		}

	    try {
	    	connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "tt", "pwd123");

		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "数据库连接失败");
			e.printStackTrace();
		}
	}
	
	public void sqlDisconnect() {
		if (connection != null) {
			  try {
			    	connection.close();
			  } catch (Exception e) {
					e.printStackTrace();
			  }
		}
	}
	
	public void add(String tableName, String attribute, String value) throws SQLException {
		if (connection == null) {
			sqlConnetct();
		}
		PreparedStatement ps = connection.prepareStatement("INSERT INTO ?(?) VALUES (?)");
		ps.setString(1, tableName);
		ps.setString(2, attribute);
		ps.setString(3, value);
		ps.executeQuery();
		
	}
	
	public void delete(String tableName, String attribute, String value) throws SQLException{
		if (connection == null) {
			sqlConnetct();
		}
		PreparedStatement ps = connection.prepareStatement("DELETE FROM ? WHERE ?");
		ps.setString(1, tableName);
		
		
	}
	
	public static void main (String[] args) {
		SqlOperation a = new SqlOperation();
		a.sqlConnetct();
	}
}
