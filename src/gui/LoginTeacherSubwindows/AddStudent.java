package gui.LoginTeacherSubwindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.function.ToLongBiFunction;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.LoginTeacher;
import guiSuperclass.Windows;
import smallTools.CheckInputs;
import smallTools.Tools;

public class AddStudent extends Add {

	private JTextField id = new JTextField(20);
	private JTextField name = new JTextField(20);
	private JTextField schoolClass = new JTextField(20);
	private JTextField major = new JTextField(20);
	
	private JLabel id_waring = new JLabel("id需要12位");
	private JLabel name_waring = new JLabel("name不能为空");
	
//	private boolean[] flags = new boolean[] {false,false};	
	
	private MouseAdapter mouseListener = new MouseAdapter() {

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseExited(e);
			Object object = e.getSource();
			if (object == id) {
				if (CheckInputs.checkId(getId())) {
					id_waring.setForeground(Color.green);
					flags[0] = true;
				} else {
					id_waring.setForeground(Color.red);
					flags[0] = false;
				}
			} else if (object == name) {
				if (CheckInputs.checkName(getName())) {
					name_waring.setForeground(Color.green);
					flags[1] = true;
				} else {
					name_waring.setForeground(Color.red);
					flags[1] = false;
				}
			}
		}
		
	};
	
	public AddStudent(Windows superWindow) {
		this.superWindow = superWindow;
		flags = new boolean[] {false,false};
		theFrame = createTheFrame("学生添加界面");
	}
	
//	private JFrame createTheFrame(String windowName) {
//		JFrame theFrame = new JFrame(windowName);
//		theFrame.getContentPane().add(createPanel());
//		theFrame.getContentPane().add(createLabels(), BorderLayout.WEST);
//		theFrame.getContentPane().add(createButtons(), BorderLayout.SOUTH);
//		//set attributes of theFrame
//		theFrame.setSize(400, 240);
//		theFrame.setLocationRelativeTo(null);
//		theFrame.setResizable(false);
////		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
////		setWindowsListenerToTheFrame();
//		return theFrame;
//	}
	
	protected JPanel createPanel() {
		JPanel onePanel = new JPanel();
		
		GridLayout layout = new GridLayout(4, 2);
		layout.setVgap(13);
		
		onePanel.setLayout(layout );
		
//		id.addActionListener(this);
//		name.addActionListener(this);
		id.addMouseListener(mouseListener);
		name.addMouseListener(mouseListener);
		
		id_waring.setForeground(Color.red);
		name_waring.setForeground(Color.red);
		
		onePanel.add(id);
		onePanel.add(id_waring);
		onePanel.add(name);
		onePanel.add(name_waring);
		onePanel.add(schoolClass);
		onePanel.add(new JLabel());
		onePanel.add(major);
		
		
		onePanel.setPreferredSize(new Dimension(100, 200));
		return onePanel;
	}
	
	protected JPanel createLabels() {
		JPanel labelsPane = new JPanel();
		labelsPane.setLayout(new GridLayout(4,1));
		
		labelsPane.add(new JLabel("id"));
		labelsPane.add(new Label("name"));
		labelsPane.add(new Label("class"));
		labelsPane.add(new Label("major"));
		
		labelsPane.setPreferredSize(new Dimension(50, 200));
		
		return labelsPane;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object == cancel) {
			superWindow.getTheFrame().setEnabled(true);
			this.diapose();
		} else if (object == confirm) {
			((LoginTeacher)superWindow).setTmpData(Tools.makeArray(getId(),getName(),getSchoolClass(),getMajor()));
			this.diapose();
		}
	}

	public String getId() {
		return id.getText();
	}

	public String getName() {
		return name.getText();
	}


	public String getSchoolClass() {
		return schoolClass.getText();
	}

	public String getMajor() {
		return major.getText();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddStudent a = new AddStudent(null);
		a.displayFram();
	}
}
