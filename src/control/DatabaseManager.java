package control;

import java.security.DrbgParameters.Reseed;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.print.attribute.PrintServiceAttribute;
import javax.swing.ComboBoxModel;

//import databaseTable.StudentWithCourseTable;
import databaseTable.row.CourseRow;
import databaseTable.row.StudentRow;
import databaseTable.row.StudentWithCourseRow;
import smallTools.TableNames;
import smallTools.Tools;
import sql.SqlOperation;

public class DatabaseManager {

	private static StudentRow stu;
	public static boolean loginToStu(String id, String password) {
		boolean re = login("student", id, password);
		if (re) {
			try {
				stu = new StudentRow();
				stu.set(SqlOperation.select("student", makeArray("*"), makeArray("id"),makeArray(id)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
	
	public static void exit() {
		stu = null;
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
		grade = Integer.valueOf(grade).toString();
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
				if (password.equals(set.getString("password"))) {
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
				ResultSet set = SqlOperation.select(TableNames.course , makeArray("*"), makeArray("id"),makeArray(courseIds[i]));
				set.next();
				courseNames[i] = set.getString("course");
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		return courseNames;
//		return new String[] {"chinese","english"};
	}
	
	public static void update(String tableName,String id, String a, String b,String c) throws SQLException {
		if (tableName.equals("student")) {
			if (id.equals("default")) {
				SqlOperation.add("final."+tableName, makeArray("name","schoolClass","major"), makeArray(a,b,c));
			}else {
				SqlOperation.update("final."+tableName, makeArray("name","schoolClass","major"), makeArray(a,b,c),
					makeArray("id"),makeArray(id));
			}
		} else if (tableName.equals("studentWithCour") ) {
			if (id.equals("default")) {
				SqlOperation.add("final."+tableName, 
						makeArray("studentId","courseId","grade"), 
						makeArray(StudentRow.getId(a),CourseRow.getId(b),c));
			} else {
				SqlOperation.update("final."+tableName, makeArray("courseId","grade"), 
						makeArray(CourseRow.getId(b),c), makeArray("studentId","courseId"), makeArray(id,CourseRow.getId(a)));
			}
		}
	}
	
	public static void delete(String tableName,String id, String a, String b,String c) {
		try {
			if (tableName.equals("student")) {
				SqlOperation.delete("final."+tableName, makeArray("id"), makeArray(id));
			}
			else if (tableName.equals("studentWithCourse")){
				SqlOperation.delete("final."+tableName, makeArray("studentId","courseId"), makeArray(id,CourseRow.getId(b)));
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static boolean existIn(String tableName, String id) {
		boolean re = false;
		try {
//			Vector<String> column_name = SqlOperation.getHeaders(tableName);
			ResultSet set = SqlOperation.selectOr(tableName,
					Tools.makeArray("id"),
					Tools.makeArray(id));
			if (set.next()) {
				re = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return re;
	}
	
	public static String getString(String tableName, String id) {
		String re = null;
		try {
			ResultSet set = SqlOperation.select(
					tableName,
					Tools.makeArray("id"),
					Tools.makeArray(id));
			if (set.next()) {
				re = set.getString(2);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return re;
	}
}
