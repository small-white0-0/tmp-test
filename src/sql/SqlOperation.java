package sql;

import java.io.ObjectInputStream.GetField;
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
	    
	   try {
		Statement st = connection.createStatement();
		st.execute("use final;");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
	
	private static String[] safeString(String[] str) {
		for (int i = 0; i < str.length; i++) {
			str[i] = "'"+str[i]+"'";
		}
		return str;
	}
	
	private static String linkString(String[] string1, String[] string2 , String s) {
		String[] re = new String[string1.length];
		for (int i = 0; i < string1.length; i++) {
			re[i] = string1[i] + "=" + string2[i];
		}
		return linkString(re, s);
	}
	
	private static String linkString(String[] string1, String[] string2 ,String s1, String s){
		String[] re = new String[string1.length];
		for (int i = 0; i < string1.length; i++) {
			re[i] = string1[i] + s1 + string2[i];
		}
		return linkString(re, s);
	}
	
	public static void add(String tableName, String[] attributes, String[] values) throws SQLException {
		if (connection == null) {
			sqlConnetct();
		}
		String sql = "INSERT INTO "+tableName+"("+linkString(attributes, ",")+")"+ " VALUES ("+linkString(safeString(values), ",")+")";
		Statement stm = connection.createStatement();
		System.out.println(sql);
		stm.execute(sql+";");
		
	}
	
	public static void delete(String tableName, String[] attributes, String[] values) throws SQLException{
		if (connection == null) {
			sqlConnetct();
		}
		Statement stm = connection.createStatement();
		String sql = "delete from "+tableName+" where "+linkString(attributes, safeString(values)," and ");
		System.out.println(sql);
		stm.execute(sql);
		
	}
	
	public static void update(String tableName, String[] setAttributes, String[] setValues, String[] attributes, String[] values) throws SQLException {
		if (connection == null) {
			sqlConnetct();
		}
		
		Statement stm = connection.createStatement();
		String sql = "update "+tableName+" set "+linkString(setAttributes, safeString(setValues), ",");
		sql += " where "+linkString(attributes, safeString(values), " and ");
		System.out.println(sql);
		stm.execute(sql+";");
	}
	
	public static void update(String tableName, String setAttributes, String setValues, String[] attributes, String[] values) throws SQLException {
		if (connection == null) {
			sqlConnetct();
		}
		
		Statement stm = connection.createStatement();
		String sql = "update "+tableName+" set "+setAttributes + "= '" + setValues+"'";
		sql += " where "+linkString(attributes, safeString(values), " and ");
		System.out.println(sql);
		stm.execute(sql+";");
	}
	
	public static ResultSet select(String tableName, String[] colNames, String[] attributes, String[] values) throws SQLException{
		if (connection == null) {
			sqlConnetct();
		}
		Statement stm = connection.createStatement();
		String sql = "select "+linkString(colNames, ",")+" from "+tableName;
		if (attributes != null && values != null) {
			sql+=" where " + linkString(attributes, safeString(values), " and ");
		}
		System.out.println(sql);
		return stm.executeQuery(sql+";");
	}
	public static ResultSet select(String tableName, String[] attributes, String[] values) throws SQLException{
		if (connection == null) {
			sqlConnetct();
		}
		Statement stm = connection.createStatement();
		String sql = "select "+" * "+" from "+tableName;
		if (attributes != null && values != null) {
			sql+=" where " + linkString(attributes, safeString(values), " and ");
		}
		return stm.executeQuery(sql+";");
	}
	
	public static ResultSet selectLike(String tableName, String[] attributes, String[] values) throws SQLException{
		if (connection == null) {
			sqlConnetct();
		}
		Statement stm = connection.createStatement();
		String sql = "select "+" * "+" from "+tableName;
		if (attributes != null && values != null) {
			for (String string : values) {
				string = "%"+string+"%";
			}
			sql+=" where " + linkString(attributes, safeString(values)," like " ," or ");
		}
		return stm.executeQuery(sql+";");
		
	}
	
	public static ResultSet selectOr(String tableName, String[] attributes, String[] values) throws SQLException{
		if (connection == null) {
			sqlConnetct();
		}
		Statement stm = connection.createStatement();
		String sql = "select "+" * "+" from "+tableName;
		if (attributes != null && values != null) {
			sql+=" where " + linkString(attributes, safeString(values), " or ");
		}
		return stm.executeQuery(sql+";");
	}
	
	public static Connection getConnection() {
		if (connection == null) {
			sqlConnetct();
		}
		return connection;
	}
	
	public static ResultSet getHeaders(String tableName) throws SQLException {
		if (connection == null) {
			sqlConnetct();
		}
		PreparedStatement ps = connection.prepareStatement("select COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_NAME='?' ;");
		ps.setString(1, tableName);
		return ps.executeQuery();
	}
	
	public static String[] makeArray(String ... str) {
		String[] re = new String[str.length];
		re = str.clone();
		return re;
	}
	public static void main(String[] args) throws SQLException {
		SqlOperation.sqlConnetct();
		System.out.println("ok");
//		ResultSet set = SqlOperation.add("final.course", makeArray("*"), makeArray("id"),makeArray("1"));
//		SqlOperation.add("course", makeArray("course","credit"), makeArray("chinese","3"));
		SqlOperation.update("final.course", makeArray("credit","course"), makeArray("10","chinese"), makeArray("course"), makeArray("10"));
		SqlOperation.delete("final.course", makeArray("course"), makeArray("chinese"));
//		set.next();
//		System.out.println(set.getString("course"));
	}
}
