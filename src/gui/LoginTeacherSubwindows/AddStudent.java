package gui.LoginTeacherSubwindows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.DatabaseManager;
import gui.LoginTeacher;
import guiSuperclass.Windows;
import smallTools.CheckInputs;
import smallTools.TableNames;
import smallTools.Tools;

public class AddStudent extends Add {

	private JTextField id = new JTextField(20);
	private JTextField name = new JTextField(20);
	private JTextField schoolClass = new JTextField(20);
	private JTextField major = new JTextField(20);
	
	private JLabel id_waring = new JLabel("id为8位，且不能重复");
	private JLabel name_waring = new JLabel("name不能为空");
	private JLabel schoolClass_warning = new JLabel("班级不能为空");
	private JLabel major_warning  = new JLabel("专业不能为空");
	
	private FocusAdapter focus = new FocusAdapter() {

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			super.focusLost(e);
			Object object = e.getSource();
			if (object == id) {
				if (CheckInputs.checkId(getId()) && !DatabaseManager.existIn(TableNames.student, getId())) {
					id_waring.setForeground(Color.green);
					flags[0] = true;
				} else {
					id_waring.setForeground(Color.red);
					flags[0] = false;
				}
			} else if (object == name) {
				if (CheckInputs.checkIsEmpty(getName())) {
					name_waring.setForeground(Color.green);
					flags[1] = true;
				} else {
					name_waring.setForeground(Color.red);
					flags[1] = false;
				}
			} else if (object == schoolClass ) {
				if (CheckInputs.checkIsEmpty(getSchoolClass())) {
					schoolClass_warning.setForeground(Color.green);
					flags[2] = true;
				} else {
					schoolClass_warning.setForeground(Color.red);
					flags[2] = false;
				}
			} else if (object == major ) {
				if (CheckInputs.checkIsEmpty(getMajor())) {
					major_warning.setForeground(Color.green);
					flags[3] = true;
				} else {
					major_warning.setForeground(Color.red);
					flags[3] = false;
				}
			}
			
		}
	};
	
	public AddStudent(Windows superWindow) {
		this.superWindow = superWindow;
		flags = new boolean[4];
		
		theFrame = createTheFrame("学生添加界面");
	}
	
	protected JPanel createPanel() {
		JPanel onePanel = new JPanel();
		
		GridLayout layout = new GridLayout(4, 2);
		layout.setVgap(13);
		
		onePanel.setLayout(layout );
		
		id.addFocusListener(focus);
		name.addFocusListener(focus);
		schoolClass.addFocusListener(focus);
		major.addFocusListener(focus);
		
		id_waring.setForeground(Color.red);
		name_waring.setForeground(Color.red);
		schoolClass_warning.setForeground(Color.red);
		major_warning.setForeground(Color.red);
		
		onePanel.add(id);
		onePanel.add(id_waring);
		onePanel.add(name);
		onePanel.add(name_waring);
		onePanel.add(schoolClass);
		onePanel.add(schoolClass_warning);
		onePanel.add(major);
		onePanel.add(major_warning);
		
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
			if (isAllRight()) {
				((LoginTeacher)superWindow).addRow(
						Tools.makeArray(getId(),getName(),getSchoolClass(),getMajor(),"123456"));
				this.diapose();
			} else {
				JOptionPane.showMessageDialog(null, "请正确填写");
			}
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
