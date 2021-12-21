package guiSuperclass;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import gui.Login;
import guiSuperclass.Before_login.Vocation;

public abstract class Before_login extends Windows {

	public enum Vocation{
		student, teacher
	}
	
	protected JButton leftButton;
	protected JButton rightButton;
	protected Vocation select = Vocation.student;
	
	
	public Before_login (String windowName, String leftButtonName,String rightButtonName) {
		JPanel selects = createSelects();
		JPanel input = createInput();
		JPanel buttons = createButtons(leftButtonName,rightButtonName);
		theFrame = createTheFrame(windowName,selects,input,buttons);
	}

	private JFrame createTheFrame(String windowName,JPanel selects, JPanel input, JPanel buttons) {
		JFrame theFrame = new JFrame(windowName);
		theFrame.getContentPane().add("North",selects);
		theFrame.getContentPane().add("Center",input);
		theFrame.getContentPane().add("South",buttons);
		theFrame.setSize(300, 300);
		theFrame.setLocationRelativeTo(null);
		theFrame.setResizable(false);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return theFrame;
	}

	private JPanel createSelects() {
//		JPanel selects = new JPanel(new FlowLayout());
		JPanel selects = new JPanel();
		CheckSelects checkSelects = new CheckSelects(this);
		//新建单选框
		ButtonGroup selectIdentify = new ButtonGroup();
		JRadioButton isStudent = new JRadioButton("学生");
		JRadioButton isTeacher = new JRadioButton("教师");
		isStudent.setSelected(true);
		isStudent.addActionListener(checkSelects);
		isTeacher.addActionListener(checkSelects);
		//添加单选框到panel中
		selectIdentify.add(isStudent);
		selectIdentify.add(isTeacher);
		selects.add(isStudent);
		selects.add(isTeacher);
		
		return selects;
	}

//	public String getSelect() {
//		return select;
//	}

	protected abstract JPanel createInput(); 
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
	private JPanel createButtons(String leftButtonName,String rightButtonName) {
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

	
	
	

//		调用： InitGlobalFont(new Font("宋体", Font.PLAIN, 12));
	
	public static void main (String[] args) {
		 InitGlobalFont(new Font("宋体", Font.PLAIN, 20));
//		login l = new login();
		 Login l = new Login();
		l.displayFram();
	}
}

class CheckSelects implements ActionListener{

	Before_login t;
	public CheckSelects(Before_login t) {
		this.t = t;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("学生")) {
			t.select = Vocation.student;
			System.out.println("xxue");
		}
		else {
			t.select = Vocation.teacher;
			System.out.println("techat");
		}
	}
	
	
}
