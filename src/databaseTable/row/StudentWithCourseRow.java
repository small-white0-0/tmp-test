package databaseTable.row;

import java.sql.ResultSet;
import java.sql.SQLException;

import sql.SqlOperation;

public class StudentWithCourseRow extends DatabaseTable {

	private DatabaseTable student;
	private DatabaseTable course;
	private int grade;
	
	public static String[] getCourseIds(String studentId) {
		String tmp="";
		try {
			ResultSet set = SqlOperation.select("studentWithCourse", makeArray("*"), makeArray("studentId"), makeArray(studentId));
			while (set.next()) {
				if (! tmp.equals("")) {
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
	
	public static String[] getGrade1(String studentId) {
		String[] re = new String[2];
		try {
			ResultSet set = SqlOperation.select("final.studentWithCourse", makeArray("studentId"), makeArray(studentId));
			set.next();
			re[0] = set.getString("grade");
			re[1] = set.getString("courseId");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
		return re;
	}
	
	public static String[] getGrade2(String courseId) {
		String[] re = new String[2];
		try {
			ResultSet set = SqlOperation.select("final.studentWithCourse", makeArray("courseId"), makeArray(courseId));
			set.next();
			re[0] = set.getString("grade");
			re[1] = set.getString("studentId");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
		return re;
	}
}
