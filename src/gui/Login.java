package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.WindowsManager;
import control.WindowsManager.WindowName;
import guiSuperclass.Before_login;
import guiSuperclass.Windows;

public class Login extends Before_login implements ActionListener{
	
	public Login() {
		super("登陆界面", "注册", "登陆");
		addButtonsListener(this, this);
	}
	
	public JPanel createInput() {
		JPanel inputs = new JPanel();
		GridLayout layout = new GridLayout();
		layout.setColumns(2);
		layout.setRows(4);
		layout.setVgap(10);
		layout.setHgap(-100);
		inputs.setLayout(layout);
		JLabel lable_id = new JLabel("id:");
		JLabel lable_passwd = new JLabel("password:");
		JTextField id = new JTextField(10);
		JPasswordField password = new JPasswordField(10);
		inputs.add(new JLabel());
		inputs.add(new JLabel());
		inputs.add(lable_id);
		inputs.add(id);
		inputs.add(lable_passwd);
		inputs.add(password);
		inputs.add(new JLabel(" "));
		inputs.add(new JLabel(" "));
		inputs.setSize(50, 60);
		return inputs;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.leftButton) {
			System.out.println("oooo");
			WindowsManager.switchWindow(WindowName.register);
		}
		else if (e.getSource() == this.rightButton && this.select == Vocation.student) {
			WindowsManager.switchWindow(WindowName.student);
		}
		else if (e.getSource() == this.rightButton && this.select == Vocation.teacher) {
			WindowsManager.switchWindow(WindowName.teacher);
		}
		
	}

	public static void main (String[] args) {
//		 InitGlobalFont(new Font("宋体", Font.PLAIN, 20));
//		login l = new login();
//		 Login l = new Login();
//		l.displayFram();
		WindowsManager.init();
	}
}
