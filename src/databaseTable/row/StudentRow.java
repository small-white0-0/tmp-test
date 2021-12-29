package databaseTable.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import control.DatabaseManager;
import sql.SqlOperation;

public class StudentRow extends DatabaseTable {

	private String id = null;
	private String name = null;
	private String schoolClass = null;
	private String major = null;
	private String password = null;
	
	public StudentRow(int id) {
//		DatabaseManager.getStudent(id);
		
	}
	
	public StudentRow() {
		
	}
	
	public void set(ResultSet set) throws SQLException {
		set.next();
		this.id = set.getString("id");
		this.name = set.getString("name");
		this.schoolClass = set.getString("schoolClass");
		this.major = set.getString("major");
		this.password = set.getString("password");
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSchoolClass() {
		return schoolClass;
	}



	public void setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
	}



	public String getMajor() {
		return major;
	}



	public void setMajor(String major) {
		this.major = major;
	}

	public String getId() {
		return id;
	}

//
//	public String[] getClassesName() {
//		return new String[] {"chind","fjdlsk"};
//	}
	
	public static String getName(String id) {
		String re = null;
		try {
			ResultSet set = SqlOperation.select("final.student", makeArray("id"), makeArray(id));
			set.next();
			re = set.getString("name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	
	public static String getId(String name) {
		String re = null;
		
		ResultSet set;
		try {
			set = SqlOperation.select("student", makeArray("*"), makeArray("name"), makeArray(name));
			set.next();
			re = set.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return re;
	}
	
}
