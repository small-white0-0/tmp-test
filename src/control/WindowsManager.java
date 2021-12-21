package control;

import java.awt.Font;
import java.sql.*;

import gui.Login;
import gui.LoginStudent;
import gui.LoginTeacher;
import gui.Register;
import guiSuperclass.Windows;

public class WindowsManager {
//	
//	public static final int REGISTER = 0;
//	public static final int LOGIN = 1;
//	public static final int STUDENT = 2;
//	public static final int TEACHER = 3;
	
	public enum WindowName{
		register, login, student, teacher
	}
	
	private static Windows registerWindow;
	private static Windows loginWindow;
	private static Windows studentWindow;
	private static Windows teacherWindow;
	private static Windows currentWindow;
	
	public static void init() {
		Windows.InitGlobalFont(new Font("宋体", Font.PLAIN, 20));
//		loginWindow = new Login();
//		currentWindow = loginWindow;
		teacherWindow = new LoginTeacher();
		currentWindow = teacherWindow;
		currentWindow.displayFram();
	}

	public static void switchWindow(WindowName toWindowName) {
		currentWindow.hideFram();
		switch (toWindowName) {
		case register:			
			if (registerWindow == null) {
				registerWindow = new Register();
			}
			currentWindow = registerWindow;
			break;
			
		case login:
			if (loginWindow == null) {
				loginWindow = new Login();
			}
			currentWindow = loginWindow;
			break;
		case student:
			if (studentWindow == null) {
				studentWindow = new LoginStudent(null);
			}
			currentWindow = studentWindow;
			break;
		case teacher:
			if (teacherWindow == null) {
				teacherWindow = new LoginTeacher();
			}
			currentWindow = teacherWindow;
			break;
		}
		currentWindow.displayFram();
	}
}