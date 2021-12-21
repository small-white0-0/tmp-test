package gui.LoginTeacherSubwindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.DatabaseManager;
import databaseTable.row.DatabaseTable;
import databaseTable.row.StudentRow;

public class InformationPanel extends JPanel implements ActionListener{

	private DatabaseTable databaseTable;
	
	private JButton searchButton;
	private JTextField searchField;
	
	private JTable showTable;
	private DefaultTableModel tableModel;
	
	public InformationPanel(DatabaseTable databaseTable) {
		this.databaseTable = databaseTable;
		
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
		
		DefaultTableModel tableModel = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 0) {
					return false;
				}
				return true;
			}
		};
//		tableModel.setColumnIdentifiers(new  String []{"id", "name","class","major"});
		tableModel.setColumnIdentifiers(header);
//		tableModel.setColumnIdentifiers(databaseTable.getTableHeader());
		tableModel.addRow(new String[] {"he"});
		tableModel.addRow(new String[] {"he"});
		tableModel.addRow(new String[] {"he"});
		
		this.tableModel = tableModel;
		JTable showTable = new JTable(tableModel);
		showTable.setRowHeight(30);
		showTable.getTableHeader().setReorderingAllowed(false);
		this.showTable = showTable;
		
		showPane.setViewportView(showTable);
		
		return showPane;
		
	}
	
	public void addRow(StudentRow student) {
		
	}
	
	public int deleteRow() {
		int selectedRow = showTable.getSelectedRow();
		if(selectedRow != -1) {
			this.tableModel.removeRow(selectedRow);
//			DatabaseManager.delete(tableModel.getValueAt(selectedRow, 0));
		}
		
		return selectedRow;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == searchButton) {
			System.out.println("更新表格");
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame a = new JFrame();
		a.getContentPane().add(new InformationPanel(null));
		
		a.setSize(500, 500);
		a.setVisible(true);
	}

}
