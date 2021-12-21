package databaseTable;

public class Student extends DatabaseTable {

	public int id = 0;
	public String name = null;
	public String schoolClass = null;
	public String major = null;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSchoolClass() {
		return schoolClass;
	}
	
	public String getMajor() {
		return major;
	}
	
	public String[] getClassesName() {
		return new String[] {"chind","fjdlsk"};
	}
	
}
