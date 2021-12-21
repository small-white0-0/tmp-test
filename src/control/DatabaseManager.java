package control;

import java.security.DrbgParameters.Reseed;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.PrintServiceAttribute;
import javax.swing.ComboBoxModel;

import databaseTable.StudentWithCourseTable;
import databaseTable.row.CourseRow;
import databaseTable.row.StudentRow;
import databaseTable.row.StudentWithCourseRow;
import sql.SqlOperation;

public class DatabaseManager {

	private static StudentRow stu;
	
	
	public static boolean loginToStu(String id, String password) {
		boolean re = login("student", id, password);
		if (re) {
			try {
				stu.set(SqlOperation.select("student", makeArray("*"), null, null));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
	
	public static StudentRow getUser() {
		return stu;
	}
	
	public static String getGrade(String course) {
		ResultSet set = null;
		String grade = null;
		try {
			String[] values = makeArray(stu.getId(),CourseRow.getId(course));
			set = SqlOperation.select("studentWithCourse", makeArray("*"), makeArray("studentId","courseId"),values);
			set.next();
			grade = set.getString("grade");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return course+": "+grade;
	}
	
	public static boolean loginToTea(String id, String password) {
		return login("teacher", id, password);
	}
	
	public static boolean login(String tableName, String id, String password) {
		boolean re = false;
		String[] colNames = new String[] {"id","password"};
		String[] attributes = new String[1];
		String[] values = new String[1];
		attributes[0] = "id";
		values[0] = id;
		ResultSet set;
		try {
			set = SqlOperation.select(tableName, colNames, attributes, values);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return re;
		}
		try {
			while (set.next()) {
				if (password == set.getString("password")) {
					re = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
		
	}
	
	private static String[] makeArray(String ... str) {
		String[] re = new String[str.length];
		re = str.clone();
		return re;
	}
	
	public static String [] getClassesName() {
		// TODO Auto-generated method stub
		String[] courseIds = StudentWithCourseRow.getCourseIds(stu.getId());
		String[] courseNames = new String[courseIds.length];
		for (int i = 0; i < courseIds.length; i++) {
			try {
				ResultSet set = SqlOperation.select(CourseRow.tabelName, makeArray("*"), makeArray("id"),makeArray(courseIds[i]));
				set.next();
				courseNames[i] = set.getString("course");
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		return courseNames;
//		return new String[] {"chinese","english"};
	}
}
