package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.MouseInputListener;

import control.WindowsManager;
import control.WindowsManager.WindowName;
import databaseTable.Student;
import gui.LoginTeacherSubwindows.InformationPanel;
import guiSuperclass.Windows;

public class LoginTeacher extends Windows implements ActionListener{

	private enum informationPane{
		student, grade
	}
	
	private JLabel studentLabel;
	private JLabel gradeLabel;
	
	private JButton addButton = new JButton("录入");
	private JButton deleteButton = new JButton("删除");
	private JButton confirmButton = new JButton("应用修改");
	private JButton exitButton = new JButton("退出");
	
	private InformationPanel studentInformation = new InformationPanel(null);
	private InformationPanel gradeInformation = new InformationPanel();
	
	private JPanel labelsPanel;
	private JPanel buttonsPanel;
	
	private LabelMouselistener labelMouselistener = new LabelMouselistener();
//	private
	
	public LoginTeacher() {
		theFrame = createTheFrame("老师");
	}
	
	
	public JFrame createTheFrame(String WindowName) {
		JFrame theFrame = new JFrame(WindowName);
		theFrame.setContentPane(new JPanel());
		// 设置布局
		BorderLayout layout = new BorderLayout();
		layout.setHgap(30);
		theFrame.getContentPane().setLayout(layout);
		//添加Lable
		this.labelsPanel = createLabels();
		theFrame.getContentPane().add(labelsPanel, BorderLayout.WEST);
		
		//添加按钮组件
		this.buttonsPanel = createButtonsPanel();
		theFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		//添加信息面板
		theFrame.getContentPane().add(studentInformation);

		//设置frame组件
		theFrame.setSize(800, 500);
		theFrame.setLocationRelativeTo(null);
		theFrame.setResizable(false);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return theFrame;
	}
	
	private JPanel createLabels() {
		JPanel labelsPanel = new JPanel(new GridLayout(6, 1));
		
		JLabel studentLabel = new JLabel("学生信息");
		studentLabel.setBackground(Color.white);
		studentLabel.setOpaque(true);
		studentLabel.addMouseListener(this.labelMouselistener);
		
		JLabel gradeLabel = new JLabel("成绩信息");
		gradeLabel.setBackground(Color.white);
		gradeLabel.addMouseListener(this.labelMouselistener);
		
		this.studentLabel = studentLabel;
		this.gradeLabel = gradeLabel;
		
		labelsPanel.add(studentLabel);
		labelsPanel.add(gradeLabel);
		return labelsPanel;
	}
	
	private JPanel createButtonsPanel() {
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		confirmButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		buttonsPanel.add(addButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(new JLabel("                                          "));
		buttonsPanel.add(exitButton);
		
		return buttonsPanel;
	}
	
	private void switchInformationpane(informationPane toPane) {
		if (toPane == informationPane.student) {
			// 切换标签颜色
			this.gradeLabel.setOpaque(false);
			this.studentLabel.setOpaque(true);
			//更改显示面板
			theFrame.getContentPane().remove(gradeInformation);
			theFrame.getContentPane().add(studentInformation);
		} else if(toPane == informationPane.grade){
			this.studentLabel.setOpaque(false);
			this.gradeLabel.setOpaque(true);
			theFrame.getContentPane().remove(studentInformation);
			theFrame.getContentPane().add(gradeInformation);
		}
		displayFram();
	}
	
	//下面是监听的实现
	

	public void actionPerformed(ActionEvent e) {
		
		Object object = e.getSource();
		
		if (object == this.addButton) {
			System.out.println("增加");
		} else if (object == this.deleteButton) {
			System.out.println("delete");
		} else if (object == this.confirmButton) {
			System.out.println("confirm");
		} else if (object == this.exitButton) {
			WindowsManager.switchWindow(WindowName.login);
		}
		
	}
	
	private class LabelMouselistener extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			Object object = e.getSource();
			
			if (object == studentLabel) {
				switchInformationpane(informationPane.student);
			} else if (object == gradeLabel) {
				switchInformationpane(informationPane.grade);
			}
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Windows.InitGlobalFont(
//		Windows.InitGlobalFont(new Font("宋体", Font.PLAIN, 20));
//		LoginTeacher l = new LoginTeacher();
//		l.displayFram();
		WindowsManager.init();
	}

}
