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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.DatabaseManager;
import databaseTable.MyTableModel;
import databaseTable.row.DatabaseTable;
import databaseTable.row.StudentRow;

public class InformationPanel extends JPanel implements ActionListener{

//	private DatabaseTable databaseTable;
//	private String[] headers;
	private JButton searchButton;
	private JTextField searchField;
	
	private JTable showTable;
	private MyTableModel firsTableModel;
	private MyTableModel tableModel;
	private int[] notEditCol;
	private Vector<String> columnNames=new Vector<String>();
	
	private String tableName;
	
	public InformationPanel(String tableName, String[] headers, int[] notEditCol) {
//		this.databaseTable = databaseTable;
//		this.headers = headers;
		for (String string : headers) {
			this.columnNames.add(string);
		}
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
		
		MyTableModel tableModel = new MyTableModel(columnNames, 1, notEditCol);
		tableModel.addRow(new String[] {"null"});
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
			for (int i = 0; i < count; i++) {
				DatabaseManager.delete(tableName,tableModel.getValueAt(selectedRow+i, 0).toString(),
						tableModel.getValueAt(selectedRow+i, 1).toString(),
						tableModel.getValueAt(selectedRow+i, 2).toString(),
						tableModel.getValueAt(selectedRow+i, 3).toString());
				
				this.tableModel.removeRow(selectedRow+i);
			}
			
		}
		
		return selectedRow;
	}
	
	
	
//	public int deleteRow() {
//		int selectedRow = showTable.getSelectedRow();
//		int count = showTable.getSelectedRowCount();
//		if(selectedRow != -1) {
////			this.tableModel.getValueAt(selectedRow, 0);
////			this.tableModel.removeRow(selectedRow);
//			this.tableModel = new myTableModel(tableModel.getDataVector(),columnNames);
//			for (int i = 0; i < count; i++) {
//				tableModel.removeRow(selectedRow);
//			}
//			this.showTable.setModel(tableModel);
//		}
//		return selectedRow;
//	}
	
//	private void removeSimilar() {
//		int flag;
//		for (int i = 0; i < tableModel.getRowCount(); i++) {
//			flag = 0;
//			for (int j = 0; j < tableModel.getRowCount(); j++) {
//				if (tableModel.getValueAt(i, j).eq) {
//					
//				}
//			}
//			
//		}
//	}
	
//	public int confirm() {
//		int index = 1;
//		removeSimilar();
//		for (int i = 0; i < tableModel.getRowCount(); i++) {
//			if () {
//				
//			}
//		}
//	}
	
	public int confirm() {
		int index = 0;
		if (tableName.equals("studnet")) {
			while (index <= tableModel.getRowCount()) {
				try {	
					DatabaseManager.update(tableName,tableModel.getValueAt(index, 0).toString(),
							tableModel.getValueAt(index, 1).toString(),
							tableModel.getValueAt(index, 2).toString(),
							tableModel.getValueAt(index, 3).toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
	//				e.printStackTrace();
					index++;
				}
			}
		}
		else {
			int i;
			boolean flag = false;
			while (index < tableModel.getRowCount()) {
				for ( i = 0; i < firsTableModel.getRowCount(); i++) {
					if (tableModel.getValueAt(index, 0).equals(firsTableModel.getValueAt(i, 0))) {
						flag=true;
						break;
					}
				}
				
				try {
					if (tableModel.getValueAt(index, 0).equals("default")) {
						DatabaseManager.update(tableName,tableModel.getValueAt(index, 0).toString(),
								tableModel.getValueAt(i, 1).toString(),
								tableModel.getValueAt(index, 2).toString(),
								tableModel.getValueAt(index, 3).toString());
					}else if (flag){
						DatabaseManager.update(tableName,tableModel.getValueAt(index, 0).toString(),
							firsTableModel.getValueAt(i, 2).toString(),
							tableModel.getValueAt(index, 2).toString(),
							tableModel.getValueAt(index, 3).toString());
					}
					tableModel.removeRow(index);
					firsTableModel.removeRow(i);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
	//				e.printStackTrace();
					index++;
				}
			}
		}
		
		return index;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchButton) {
			System.out.println("更新表格");
			try {
				DatabaseManager.getInfo(tableName,this.searchField.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MyTableModel tabelModel = new MyTableModel(columnNames, 0, notEditCol);
			String[] dataRow = DatabaseManager.getInfo(tableName);
			while (dataRow != null) {
				tabelModel.addRow(dataRow);
				dataRow = DatabaseManager.getInfo(tableName);
			}
			this.tableModel = tabelModel;
			this.showTable.setModel(tabelModel);
			this.firsTableModel = this.tableModel;
		}
	}
}
