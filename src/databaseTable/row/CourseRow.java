package databaseTable.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseTable.row.DatabaseTable;
import sql.SqlOperation;

public class CourseRow extends DatabaseTable {

	public static final String tabelName = "course";
	private String id;
	private String courseName;
	private int credit;
	
	public CourseRow() {
		
	}
	
	public CourseRow(String id, String courseName, int credit) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.credit = credit;
	}

	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}

	public static String getId(String course) {
		String re = null;
		
		ResultSet set;
		try {
			set = SqlOperation.select("course", makeArray("*"), makeArray("course"), makeArray(course));
			set.next();
			re = set.getString("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return re;
	}
	
	public static String getCourseName(String id) {
		String re = null;
		try {
			ResultSet set = SqlOperation.select("final.course", makeArray("id"), makeArray(id));
			set.next();
			re = set.getString("course");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return re;
	}
	@Override
	public String[] getTableHeader() {
		
		return null;
	}
	
	
	
}
