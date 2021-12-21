package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.swing.JOptionPane;

import databaseTable.row.CourseRow;

public class SqlOperation {

	private static Connection connection;
	
	public static void sqlConnetct() {
		
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
//	    
//	   try {
//		Statement st = connection.createStatement();
//		st.execute("use final;");
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	}
	
	public static void sqlDisconnect() {
		if (connection != null) {
			  try {
			    	connection.close();
			  } catch (Exception e) {
					e.printStackTrace();
			  }
		}
	}
	
	private static String linkString(String[] string, String s) {
		String re="";
		for(String str : string) {
			if (!str.equals(string[0])) {
				re = re +s;
			}
			re = re + str;
		}
		
		return re;
	}
	
	private static String linkString(String[] string1, String[] string2 , String s) {
		for (int i = 0; i < string1.length; i++) {
			string1[i] += "=" + string2[i];
		}
		return linkString(string1, s);
	}
	
//	private static String linkString(String[] string1, String[] string2 , String s,String s2) {
//		for (int i = 0; i < string1.length; i++) {
//			string1[i] += s2 + string2[i];
//		}
//		return linkString(string1, s);
//	}
	
	
	public static void add(String tableName, String[] attributes, String[] values) throws SQLException {
		if (connection == null) {
			sqlConnetct();
		}
		PreparedStatement ps = connection.prepareStatement("INSERT INTO ?(?) VALUES (?) ;");
		ps.setString(1, tableName);
		ps.setString(2, linkString(attributes,","));
		ps.setString(3, linkString(values, ","));
		ps.executeQuery();
		
	}
	
	public static void delete(String tableName, String[] attributes, String[] values) throws SQLException{
		if (connection == null) {
			sqlConnetct();
		}
		PreparedStatement ps = connection.prepareStatement("DELETE FROM ? WHERE ? ;");
		ps.setString(1, tableName);
		ps.setString(2, linkString(attributes, values, " and "));
		ps.execute();
		
	}
	
	public static void update(String tableName, String[] setAttributes, String[] setValues, String[] attributes, String[] values) throws SQLException {
		if (connection == null) {
			sqlConnetct();
		}
		PreparedStatement ps = connection.prepareStatement("update ? set ? ?;");
		ps.setString(1, tableName);
		ps.setString(2, linkString(setAttributes, setValues, ","));
		ps.setString(3, "where" + linkString(attributes, values, " and "));
		ps.execute();
	}
	
	public static ResultSet select(String tableName, String[] colNames, String[] attributes, String[] values) throws SQLException{
		if (connection == null) {
			sqlConnetct();
		}
//		PreparedStatement ps = connection.prepareStatement("select ? from ? ?;");
//		ps.setString(1, linkString(colNames, ","));
//		ps.setString(2, tableName);
		Statement stm = connection.createStatement();
		String sql = "select "+linkString(colNames, ",")+" from "+tableName;
		if (attributes != null && values != null) {
//			ps.setString(3, "  where " + linkString(attributes, values, " and "));
			sql+=" where " + linkString(attributes, values, " and ");
		} else {
//			ps.setString(3, null);
		}
		return stm.executeQuery(sql+";");
	}
	
	
	
	
	public static ResultSet getHeaders(String tableName) throws SQLException {
		if (connection == null) {
			sqlConnetct();
		}
		PreparedStatement ps = connection.prepareStatement("select COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_NAME='?' ;");
		ps.setString(1, tableName);
		return ps.executeQuery();
	}
	
	private static String[] makeArray(String ... str) {
		String[] re = new String[str.length];
		re = str.clone();
		return re;
	}
	public static void main(String[] args) throws SQLException {
		SqlOperation.sqlConnetct();
		System.out.println("ok");
		SqlOperation.select("final.course", makeArray("*"), makeArray("id"),makeArray("1"));
	}
}
