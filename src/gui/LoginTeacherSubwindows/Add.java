package gui.LoginTeacherSubwindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import guiSuperclass.Windows;

public abstract class Add extends Windows implements ActionListener{

	protected Windows superWindow;
	
	protected JButton cancel = new JButton("取消");
	protected JButton confirm = new JButton("确定");
	
	protected boolean[] flags;
	
	
	protected void setWindowsListenerToTheFrame() {
		
		theFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);
				superWindow.getTheFrame().setEnabled(false);
			}

//			@Override
//			public void windowClosing(WindowEvent e) {
//				// TODO Auto-generated method stub
//				super.windowClosing(e);
//				superWindow.setEnabled(true);
//			}
			
		});
	}
	
	
	public JFrame createTheFrame(String windowName) {
		JFrame theFrame = new JFrame(windowName);
		theFrame.getContentPane().add(createPanel());
		theFrame.getContentPane().add(createLabels(), BorderLayout.WEST);
		theFrame.getContentPane().add(createButtons(), BorderLayout.SOUTH);
		//set attributes of theFrame
		theFrame.setSize(400, 240);
		theFrame.setLocationRelativeTo(null);
		theFrame.setResizable(false);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setWindowsListenerToTheFrame();
		return theFrame;
	}
	
	protected abstract JPanel createLabels();
	protected abstract JPanel createPanel();
	
	private JPanel createButtons() {
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		cancel.addActionListener(this);
		confirm.addActionListener(this);
		
		buttonsPanel.add(cancel);
		buttonsPanel.add(confirm);
		return buttonsPanel;
	}
	
	protected boolean isAllRight() {
		boolean re = true;
		for (boolean b : flags) {
			if (b == false) {
				re = false;
				break;
			}
		}
		return re;
	}


	@Override
	public void diapose() {
		// TODO Auto-generated method stub
		superWindow.getTheFrame().setEnabled(true);
		super.diapose();
	}
	
}
