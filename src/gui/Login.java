package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import data.User;

public class Login {

	private User person;
	JFrame loginFrame;
	JPanel selects;
	JPanel input;
	JPanel buttons;
	JPanel space = new JPanel();
	public Login() {
//		space.add(new JLabel(" "));
		selects = createSelects();
		input = createInput();
		buttons = createButtons();
		loginFrame = createTheFrame();

	}

	public JFrame createTheFrame() {
		JFrame loginFrame = new JFrame("登陆界面");
//		loginFrame.setLayout(new FlowLayout());
//		loginFrame.setLayout(new GridLayout(0, 1, -300, 0));
		loginFrame.getContentPane().add("North",selects);
//		loginFrame.getContentPane().add(new Label("           "));
		loginFrame.getContentPane().add("Center",input);
		loginFrame.getContentPane().add("South",buttons);
		loginFrame.setSize(300, 300);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setResizable(false);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return loginFrame;
	}

	public void resetLoginFram() {
		loginFrame.removeAll();
		loginFrame.setLayout(new FlowLayout());
//		loginFrame.setLayout(new GridLayout(3, 3));
		loginFrame.getContentPane().add(selects);
		loginFrame.getContentPane().add(input);
		loginFrame.getContentPane().add(buttons);
		loginFrame.setSize(700, 800);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setResizable(false);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JPanel createSelects() {
//		JPanel selects = new JPanel(new FlowLayout());
		JPanel selects = new JPanel();
		//新建单选框
		ButtonGroup selectIdentify = new ButtonGroup();
		JRadioButton isStudent = new JRadioButton("学生");
		JRadioButton isTeacher = new JRadioButton("教师");
		//添加单选框到panel中
		selectIdentify.add(isStudent);
		selectIdentify.add(isTeacher);
		selects.add(isStudent);
		selects.add(isTeacher);
		return selects;
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

	public JPanel createButtons() {
		JPanel buttons = new JPanel();
		JButton register = new JButton("注册");
		JButton enter = new JButton("登陆");
		buttons.add(register);
		buttons.add(enter);
		return buttons;
		
	}

	void displayFram() {
		loginFrame.setVisible(true);
	}
	
	
	
	
	  private static void InitGlobalFont(Font font) {
		    FontUIResource fontRes = new FontUIResource(font);
		    for (Enumeration<Object> keys = UIManager.getDefaults().keys();
		         keys.hasMoreElements(); ) {
		      Object key = keys.nextElement();
		      Object value = UIManager.get(key);
		      if (value instanceof FontUIResource) {
		        UIManager.put(key, fontRes);
		      }
		    }
	  }

//		调用： InitGlobalFont(new Font("宋体", Font.PLAIN, 12));
	
	public static void main (String[] args) {
		 InitGlobalFont(new Font("宋体", Font.PLAIN, 20));
//		login l = new login();
		 Login l = new Login();
		l.displayFram();
	}
}
