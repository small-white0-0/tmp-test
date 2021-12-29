package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.DatabaseManager;
import control.WindowsManager;
import control.WindowsManager.WindowName;
import databaseTable.row.DatabaseTable;
import databaseTable.row.StudentRow;
import guiSuperclass.Windows;

public class LoginStudent extends Windows implements ActionListener{
	
	StudentRow student = DatabaseManager.getUser();
	
	JComboBox<String> className;
	JTextField gradeShow;
	JButton exitButton;
	
	public LoginStudent() {
		this.student = student;
//		JPanel infomation = createInfomationPanel();
//		JPanel grade = createGradePanel();
//		theFrame = createFrame("学生",infomation,grade);
		theFrame = createTheFrame("学生");
	}
	
	private JFrame createTheFrame(String windowName) {
		JFrame theFrame = new JFrame(windowName);
		theFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		theFrame.getContentPane().add("West", createInfomationPanel());
		theFrame.getContentPane().add(createGradePanel());
		theFrame.setSize(500, 400);
		theFrame.setLocationRelativeTo(null);
		theFrame.setResizable(false);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return theFrame;
	}
	private JPanel createInfomationPanel() {
		JPanel infomation = new JPanel();
		JLabel id = new JLabel("学号:"+student.getId());
		JLabel name = new JLabel("姓名:"+student.getName());
		JLabel schoolClass = new JLabel("班级:"+student.getSchoolClass());
		JLabel major = new JLabel("专业:"+student.getMajor());
		GridLayout layout = new GridLayout();
		layout.setColumns(1);
		layout.setRows(4);
		layout.setVgap(10);
		infomation.setLayout(layout);
		infomation.add(id);
		infomation.add(name);
		infomation.add(schoolClass);
		infomation.add(major);
//		infomation.setBorder(new LineBorder(Color.blue));
		infomation.setBackground(Color.white);
		infomation.setPreferredSize(new Dimension(160, 300));
		return infomation;
	}
	
	private JPanel createGradePanel() {
		JPanel grade = new JPanel();
		grade.setLayout(null);
		//课程选择框
		JComboBox<String> classesName = new JComboBox<String>(DatabaseManager.getClassesName());
//		JComboBox<String> classesName = new JComboBox<String>(new String[] {"ok"});
//		classesName.setBackground(Color.black);
		classesName.setBounds(120, 20, 100, 45);
		classesName.addActionListener(this);
		this.className = classesName;
		
		//课程成绩显示框
		JTextField gradeShow = new JTextField();
		gradeShow.setBackground(Color.white);
		gradeShow.setBounds(50, 100, 230, 80);
		gradeShow.setEditable(false);
		gradeShow.setText(DatabaseManager.getGrade(classesName.getSelectedItem().toString()));
		this.gradeShow = gradeShow;
		
		//退出按钮
		JButton exitButton = new JButton("退出");
		exitButton.setBounds(260, 300, 70, 60);
		exitButton.addActionListener(this);
		grade.add(exitButton);
		this.exitButton = exitButton;
		
		grade.add(classesName);
		grade.add(gradeShow);
		return grade;
		
	}
	
	public void reset() {
		theFrame.dispose();
		theFrame = createTheFrame("学生");
	}
	
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == this.className) {
			String grade = DatabaseManager.getGrade(className.getSelectedItem().toString());
			this.gradeShow.setText(grade);
		} else {
			WindowsManager.switchWindowSafe(WindowName.login);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		StudentRow a = new StudentRow();
		LoginStudent n = new LoginStudent();
		n.displayFram();
	}

}
