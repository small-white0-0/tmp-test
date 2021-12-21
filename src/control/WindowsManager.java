package control;

import java.awt.Font;
import java.sql.*;

import javax.swing.JOptionPane;

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
		loginWindow = new Login();
		currentWindow = loginWindow;
//		teacherWindow = new LoginTeacher();
//		currentWindow = teacherWindow;
		currentWindow.displayFram();
	}
	
	public static void switchWindowSafe(WindowName toWindowName) {
		switch (toWindowName) {
		case register:
			switchWindow(toWindowName);
			break;
		case login:
			switchWindow(toWindowName);
			break;
		case student:
			if (DatabaseManager.loginToStu(((Login) currentWindow).getId(),((Login) currentWindow).getPassword())) {
				switchWindow(toWindowName);
			} else {
				JOptionPane.showMessageDialog(null, "帐号或密码错误");
			}
		case teacher:
			if (DatabaseManager.loginToTea(((Login) currentWindow).getId(),((Login) currentWindow).getPassword())) {
				switchWindow(toWindowName);
			} else {
				JOptionPane.showMessageDialog(null, "帐号或密码错误");
			}
		default:
			break;
		}
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
				studentWindow = new LoginStudent();
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