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
import smallTools.Tools;
import sql.SqlOperation;

public class DatabaseManager {

	private static StudentRow stu;
	private static ResultSet info_set_stu;
//	private static ResultSet info_set_course;
	
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
	
	public static void getInfo(String tableName, String regex) throws SQLException{
		if (tableName.equals("student")) {
			if (regex.equals("*")) {
				info_set_stu = SqlOperation.selectLike("final."+tableName, null, null);
			} else {
				info_set_stu = SqlOperation.selectLike("final."+tableName, 
						makeArray("id","name","schoolClass","major"), 
						makeArray(regex,regex,regex,regex));
				
			}
		}else if (tableName.equals("studentWithCourse")) {
			
			if (regex.equals("*")) {
				info_set_stu = SqlOperation.selectLike("final."+tableName, null, null);
//				info_set_stu = SqlOperation.selectOr("final.student", null,null);
//				info_set_course = SqlOperation.select("final.course" ,null, null);
			} else {
				info_set_stu = SqlOperation.select("final."+tableName, makeArray("studentId","courseId"), makeArray(regex,regex));
				///
//				Connection con = SqlOperation.getConnection();
//				String sql = "select * from final.student where id=? OR name=? or schoolClass=? or major=?;";
//				PreparedStatement ps = con.prepareStatement(sql);
//				for(int i =1;i<7;i++) {
//					ps.setString(1, regex);
//				}
//				info_set_stu = ps.executeQuery();
//				
//				sql = "select * from final.course where id=? or course=? ";
//				ps = con.prepareStatement(sql);
//				for(int i =1;i<3;i++) {
//					ps.setString(1, regex);
//				}
//				info_set_course = ps.executeQuery();
				////
				
			}
			
		
		}
	}
	public static String[] getInfo(String tableName) {
		String[] re = null;
		try {
			if (info_set_stu.next()) {
				 re = new String[4];
				if (tableName.equals("student")) {
					re[0] = info_set_stu.getString("id");
					re[1] = info_set_stu.getString("name");
					re[2] = info_set_stu.getString("schoolClass");
					re[3] = info_set_stu.getString("major");
				} else {
//					String[] tmp;
//					while (true) {
//						if (info_set_stu.next()) {
//							tmp = StudentWithCourseRow.getGrade1(info_set_stu.getString("id"));
//							if (tmp != null) {
//								re[0] = info_set_stu.getString("id");
//								re[1] = info_set_stu.getNString("name");
//								re[2] = CourseRow.getCourseName(tmp[1]);
//								re[3] = tmp[0];
//								break;
//							}
//						}else if (info_set_course.next()) {
//							tmp = StudentWithCourseRow.getGrade2(info_set_course.getString("id"));
//							if (tmp != null) {
//								re[0] = tmp[1];
//								re[1] = StudentRow.getName(tmp[1]);
//								re[2] = info_set_course.getString("course");
//								re[3] = tmp[0];
//								break;
//							}
//						} else {
//							re = null;
//							break;
//						}
//					}
					re[0] = info_set_stu.getString("studentId");
					re[1] = StudentRow.getName(re[0]);
					re[2] = CourseRow.getCourseName(info_set_stu.getString("courseId"));
					re[3] = Integer.valueOf(info_set_stu.getString("grade")).toString();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return re;
	}
	
	public static boolean existIn(String str, String tableName) {
		boolean re = false;
		try {
			Vector<String> column_name = SqlOperation.getHeaders(tableName);
			ResultSet set = SqlOperation.selectOr(tableName,
					Tools.makeArray(column_name),
					Tools.makeArray(str, column_name.size()));
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
}
