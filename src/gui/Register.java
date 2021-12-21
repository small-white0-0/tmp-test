package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import checks.CheckInputs;
import control.WindowsManager;
import control.WindowsManager.WindowName;
import guiSuperclass.Before_login;

public class Register extends Before_login implements ActionListener {

	private JTextField id;
	private JPasswordField firstPasswd;
	private JPasswordField secondPasswd;
	private JLabel warning;
	
	private String idWarning = "id应是12位的数字";
	private String pwdWarning = "密码不一致";
	
	public Register() {
		super("注册界面", "返回", "注册");
		theFrame.getContentPane().add(new JPanel(), BorderLayout.NORTH);
		addButtonsListener(this, this);
	}

	public JPanel createInput () {
		JPanel inputs = new JPanel();
		//设置布局
		GridLayout layout = new GridLayout();
		layout.setColumns(2);
		layout.setRows(5);
		layout.setVgap(10);
		layout.setHgap(-100);
		inputs.setLayout(layout);
		//创建组件
		JLabel lable_id = new JLabel("id:");
		JLabel lable_passwd = new JLabel("密码");
		JTextField id = new JTextField(10);
		JPasswordField password = new JPasswordField(10);
		JLabel lable_pwd2 = new JLabel("确认密码");
		JPasswordField password2 = new JPasswordField(10);
		JLabel warning = new JLabel();
		warning.setForeground(Color.red);
		warning.setFont(new Font("宋体", 0, 10));
//		warning.setVisible(false);
		//添加组件到JPanel
		inputs.add(new JLabel());
		inputs.add(new JLabel());
		inputs.add(lable_id);
		inputs.add(id);
		inputs.add(lable_passwd);
		inputs.add(password);
		inputs.add(lable_pwd2);
		inputs.add(password2);
		inputs.add(warning);
//		inputs.setSize(50, 60);
		
		this.id = id;
		this.firstPasswd = password;
		this.secondPasswd = password2;
		this.warning = warning;
		return inputs;
	}
	
	
	private String getId() {
		return id.getText();
	}

	private String getFirstPasswd() {
		String fisrtPasswd = new String(this.firstPasswd.getPassword());
		return fisrtPasswd;
	}

	private String getSencondPasswd() {
		String secondPasswd = new String(this.secondPasswd.getPassword());
		return secondPasswd;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.leftButton) {
			
			WindowsManager.switchWindowSafe(WindowName.login);
			
		} else if (e.getSource() == this.rightButton) {
			
			if (! CheckInputs.checkid(this.getId())) {
				this.warning.setText(idWarning);
			} else if (! getFirstPasswd().equals(getSencondPasswd())){
				this.warning.setText(pwdWarning);
			} else {
				this.warning.setText(null);
				
				if (this.select == Vocation.student) {
					WindowsManager.switchWindowSafe(WindowName.student);
				}
				else {
					WindowsManager.switchWindowSafe(WindowName.teacher);
				}
				
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Register n = new Register();
		n.displayFram();
	}

}
