package gui.LoginTeacherSubwindows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.jar.Attributes.Name;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.TextUI;

import control.DatabaseManager;
import gui.LoginTeacher;
import guiSuperclass.Windows;
import smallTools.CheckInputs;
import smallTools.TableNames;
import smallTools.Tools;

public class AddGrade extends Add {

	private JTextField student = new JTextField(20);
	private JTextField course = new JTextField(20);
	private JTextField grade = new JTextField(20);
	
	private JLabel student_warning = new JLabel("学生id不存在");
	private JLabel course_warning = new JLabel("课程id不存在");
	private JLabel grade_waring = new JLabel("分数范围是0～100");
	
	private FocusAdapter focus = new FocusAdapter() {

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			super.focusLost(e);
			Object object = e.getSource();
			if (object == student) {
				if (DatabaseManager.existIn(getStudent(),TableNames.student)) {
					student_warning.setForeground(Color.green);
					flags[0] = true;
				} else {
					student_warning.setForeground(Color.red);
					flags[0] = false;
				}
			} else if (object == course){
				if (DatabaseManager.existIn(getCourse(),TableNames.course)) {
					course_warning.setForeground(Color.green);
					flags[1] = true;
				} else {
					course_warning.setForeground(Color.red);
					flags[1] = false;
				}
			} else if (object == grade) {
				if (CheckInputs.checkGrade(getGrade())) {
					grade_waring.setForeground(Color.green);
					flags[2] = true;
				} else {
					grade_waring.setForeground(Color.red);
					flags[2] = false;
				}
			}
		}
		
	};
	
	public AddGrade(Windows superWindows) {
		this.superWindow = superWindows;
		flags = new boolean[] {false,false,false};
		theFrame = createTheFrame("成绩添加界面");
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
						Tools.makeArray(getStudent(),getCourse(),getGrade()));
				superWindow.getTheFrame().setEnabled(true);
				this.diapose();
			} else {
				JOptionPane.showMessageDialog(null, "请正确填写");
			}
			
		}
	}

	@Override
	protected JPanel createPanel() {
		JPanel onePane = new JPanel();
		GridLayout layout = new GridLayout(3,2);
//		layout.setHgap(30);
		layout.setVgap(20);
		onePane.setLayout(layout);
		
		// add listener
		student.addFocusListener(focus);
		course.addFocusListener(focus);
		grade.addFocusListener(focus);
		
		//set color
		student_warning.setForeground(Color.red);
		course_warning.setForeground(Color.red);
		grade_waring.setForeground(Color.red);
		
//		add compentment
		onePane.add(student);
		onePane.add(student_warning);
		onePane.add(course);
		onePane.add(course_warning);
		onePane.add(grade);
		onePane.add(grade_waring);
		
		onePane.setPreferredSize(new Dimension(100,170));
		return onePane;
	}

	@Override
	protected JPanel createLabels() {
		JPanel labels = new JPanel(new GridLayout(3,1));
		
		labels.add(new JLabel("student_id"));
		labels.add(new JLabel("course_id"));
		labels.add(new JLabel("grade"));
		
		labels.setPreferredSize(new Dimension(80, 200));
		return labels;
	}

	public String getStudent() {
		return student.getText();
	}

	public String getCourse() {
		return course.getText();
	}

	public String getGrade() {
		return grade.getText();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddGrade a = new AddGrade(null);
		a.displayFram();
	}

}
