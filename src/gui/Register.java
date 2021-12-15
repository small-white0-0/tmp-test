package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends Login {

	@Override	
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
		JLabel lable_passwd = new JLabel("password:");
		JTextField id = new JTextField(10);
		JPasswordField password = new JPasswordField(10);
		JLabel lable_pwd2 = new JLabel("确认密码");
		JPasswordField password2 = new JPasswordField(10);
		//添加组件到JPanel
		inputs.add(new JLabel());
		inputs.add(new JLabel());
		inputs.add(lable_id);
		inputs.add(id);
		inputs.add(lable_passwd);
		inputs.add(password);
		inputs.add(lable_pwd2);
		inputs.add(password2);
		inputs.setSize(50, 60);
		return inputs;
	}
	
	public JPanel createButtons() {
		JPanel buttons = new JPanel();
		JButton register = new JButton("返回");
		JButton enter = new JButton("注册");
		buttons.add(register);
		buttons.add(enter);
		return buttons;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Register n = new Register();
		n.displayFram();
	}

}
