package databaseTable;

import databaseTable.DatabaseTable;

public class Course extends DatabaseTable {

	private String id;
	private String courseName;
	private int credit;
	
	public Course() {
		
	}
	
	public Course(String id, String courseName, int credit) {
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

	@Override
	public String[] getTableHeader() {
		
		return null;
	}
	
	
	
}
