package databaseTable.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import sql.SqlOperation;

public class StudentWithCourseRow extends DatabaseTable {

	private DatabaseTable student;
	private DatabaseTable course;
	private int grade;
	
	public static String[] getCourseIds(String studentId) {
		String tmp=null;
		try {
			ResultSet set = SqlOperation.select("course", makeArray("*"), makeArray("studentId"), makeArray(studentId));
			while (set.next()) {
				if (tmp != null) {
					tmp +=":";
				}
				tmp += set.getString("courseId");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tmp.split(":");
	}
}
