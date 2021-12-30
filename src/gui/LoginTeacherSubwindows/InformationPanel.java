package gui.LoginTeacherSubwindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.DatabaseManager;
import databaseTable.MyTableModel;
import databaseTable.row.DatabaseTable;
import databaseTable.row.StudentRow;
import smallTools.Procedure;

public class InformationPanel extends JPanel implements ActionListener{

//	private DatabaseTable databaseTable;
//	private String[] headers;
	private JButton searchButton;
	private JTextField searchField;
	
	private JTable showTable;
	private MyTableModel firsTableModel;
	private MyTableModel tableModel;
	private int[] notEditCol;
//	private Vector<String> columnNames=new Vector<String>();
	
	private String tableName;
	
	public InformationPanel(String tableName, String[] headers, int[] notEditCol) {
//		this.databaseTable = databaseTable;
//		this.headers = headers;
//		for (String string : headers) {
//			this.columnNames.add(string);
//		}
		this.notEditCol = notEditCol;
		this.tableName = tableName;
		
		
		this.setLayout(new BorderLayout());
		this.add(createInputPanel(),BorderLayout.NORTH);
		this.add(createShowPane());
		this.setBackground(Color.white);
		this.setOpaque(true);
	}
	
	private JPanel createInputPanel() {
		
		JPanel inputPanel = new JPanel(new FlowLayout());
		
		JTextField searchField = new JTextField(25);
		this.searchField = searchField;
		inputPanel.add(searchField);
		
		JButton searchButton = new JButton("搜索");
		searchButton.addActionListener(this);
		this.searchButton = searchButton;
		inputPanel.add(searchButton);
		
		return inputPanel;
	}

	private JScrollPane createShowPane() {
		JScrollPane showPane = new JScrollPane();
		
		MyTableModel tableModel = null;
		tableModel = new MyTableModel(tableName, notEditCol);
		
//		tableModel.addRow(new String[] {"null"});
		this.tableModel = tableModel;
		this.firsTableModel = tableModel;
		JTable showTable = new JTable(tableModel);
		showTable.setRowHeight(30);
		showTable.getTableHeader().setReorderingAllowed(false);
		this.showTable = showTable;
		
		showPane.setViewportView(showTable);
		
		return showPane;
		
	}
	
	public void scrollToRow(int row){
		int value = showTable.getRowHeight()*row+1;
		((JScrollPane)showTable.getParent().getParent()).getVerticalScrollBar().setValue(value);
	}
	
	
	
	public void addRow() {
		this.tableModel.addRow(new String[] {"default"});
		scrollToRow(showTable.getRowCount());
	}
	
	public int deleteRow() {
		int selectedRow = showTable.getSelectedRow();
		int count = showTable.getSelectedRowCount();
		if(selectedRow != -1) {
			this.tableModel.removeRow(selectedRow);
		}
		
		return selectedRow;
	}
	
	public void setProcedureToTableModel(int index, Procedure procedure) {
		this.tableModel.setProcedure(procedure, index);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchButton) {
			System.out.println("更新表格");
			String searchString = this.searchField.getText().toString();
			Procedure[] tmpProcedures = this.tableModel.getProcedures();
			if (searchString.equals("")) {
				this.tableModel = new MyTableModel(tableName, notEditCol);
			} else {
				this.tableModel = new MyTableModel(tableName, notEditCol, searchString);
			}
			this.tableModel.setProcedures(tmpProcedures);
			this.showTable.setModel(tableModel);
		}
	}
}
