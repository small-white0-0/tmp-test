package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import data.User;

public abstract class Before_login extends Windows {

	private JFrame theFrame;
	private JPanel selects;
	private JPanel input;
	private JPanel buttons;
//	private JTextField id;
//	private JPasswordField passwd;
	private JButton leftButton;
	private JButton rightButton;
	
//	JPanel space = new JPanel();
	
	
	public Before_login (String windowName, String leftButtonName,String rightButtonName) {
		selects = createSelects();
		input = createInput();
		buttons = createButtons(leftButtonName,rightButtonName);
		theFrame = createTheFrame(windowName);
	}

	public JFrame createTheFrame(String windowName) {
		JFrame theFrame = new JFrame(windowName);
//		loginFrame.setLayout(new FlowLayout());
//		loginFrame.setLayout(new GridLayout(0, 1, -300, 0));
		theFrame.getContentPane().add("North",selects);
		theFrame.getContentPane().add("Center",input);
		theFrame.getContentPane().add("South",buttons);
		theFrame.setSize(300, 300);
		theFrame.setLocationRelativeTo(null);
		theFrame.setResizable(false);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return theFrame;
	}

	public void resetLoginFram() {
		theFrame.removeAll();
		theFrame.setLayout(new FlowLayout());
//		loginFrame.setLayout(new GridLayout(3, 3));
		theFrame.getContentPane().add(selects);
		theFrame.getContentPane().add(input);
		theFrame.getContentPane().add(buttons);
		theFrame.setSize(700, 800);
		theFrame.setLocationRelativeTo(null);
		theFrame.setResizable(false);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


	public abstract JPanel createInput(); 
/*	{
	JPanel inputs = new JPanel();
		// 设置布局
		GridLayout layout = new GridLayout();
		layout.setColumns(2);
		layout.setRows(4);
		layout.setVgap(10);
		layout.setHgap(-100);
		inputs.setLayout(layout);
		JLabel lable_id = new JLabel("id:");
		JLabel lable_passwd = new JLabel("password:");
		//创建输入框
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
		// 记录输入输出框的引用
		this.id=id;
		this.passwd = password;
		return inputs;
	}
*/
	public JPanel createButtons(String leftButtonName,String rightButtonName) {
		JPanel buttons = new JPanel();
		JButton leftButton = new JButton(leftButtonName);
		JButton rightButton = new JButton(rightButtonName);
		buttons.add(leftButton);
		buttons.add(rightButton);
		//记录按钮
		this.rightButton = rightButton;
		this.leftButton = leftButton;
		return buttons;
	}
	
	public void addButtonsListener(ActionListener leftActionListener, ActionListener rightActionListener) {
		leftButton.addActionListener(leftActionListener);
		rightButton.addActionListener(rightActionListener);
	}

	public void displayFram() {
		theFrame.setVisible(true);
	}
	
	public void hideFram() {
		theFrame.setVisible(false);
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
