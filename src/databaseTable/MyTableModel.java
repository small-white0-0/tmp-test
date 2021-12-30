package databaseTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import smallTools.Procedure;
import smallTools.Tools;
import sql.SqlOperation;

public class MyTableModel extends DefaultTableModel{
	
	private int[] disEdit;
	private String tableName = null;
	private Procedure[] procedures;
	
	
	private MyTableModel(String tableName) {
		this.tableName = tableName;
		try {
			this.columnIdentifiers = SqlOperation.getHeaders(tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "查表错误");
		}
		this.procedures = new Procedure[super.getColumnCount()];
	}
	
	public MyTableModel(String tableName , int[] disEdit) {
		this(tableName);
		this.disEdit = disEdit;
		try {
			this.getTableData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据获取错误");
		}
	}
	
	public MyTableModel(String tableName, int[] disEdit, String str){
		this(tableName , disEdit);
		try {
			this.getTableData(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "查表失败");
		}
	}
	
	public MyTableModel(String tableName,int[] disEdit, String[] attributes, String[] values){
		this(tableName, disEdit);
		try {
			this.getTableData(attributes, values);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据获取错误");
		}
	}
	
	
//	public MyTableModel(Vector<?> columnNames, int rowCount, int[] disEdit) {
//		super(columnNames,rowCount);
//		this.disEdit = disEdit;
//	}
//	
//	public MyTableModel(Vector<Vector> dataVector, Vector<String> columnNames) {
//		// TODO Auto-generated constructor stub
//	}
	
	public void setColumnsIsEditable(int[] disEdit) {
		this.disEdit = disEdit;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (disEdit == null) {
			return true;
		} else {
			for (int col : this.disEdit) {
				if (columnIndex == col) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	private void getTableData(String[] attributes, String[] values) throws SQLException {
		this.dataVector.removeAllElements();
		ResultSet set = SqlOperation.select(tableName, attributes, values);
		dataVector.removeAllElements();
		String[] row = new String[columnIdentifiers.size()];
		while (set.next()) {
			for (int i = 0; i < row.length; i++) {
				row[i] = set.getString(columnIdentifiers.get(i).toString());
			}
			addRow(row);
		}
	}
	
	private void getTableData(String str) throws SQLException {
		this.dataVector.removeAllElements();
		ResultSet set =  SqlOperation.selectLike(tableName, 
				Tools.makeArray(this.columnIdentifiers.subList(0, getColumnCount()-1).toArray()),
				Tools.makeArray(str,this.getColumnCount()));
		String[] row = new String[this.getColumnCount()];
		while(set.next()) {
			for (int i = 0; i < row.length; i++) {
				row[i] = set.getString(this.columnIdentifiers.get(i).toString());
			}
			this.addRow(row);
		}
	}
	
	private void getTableData() throws SQLException{
		getTableData(null, null);
	}
	
	public void setProcedure(Procedure procedure, int columnIndex) {
		if (columnIndex >= 0 ) {
			this.procedures[columnIndex] = procedure;
		} else if (columnIndex < 0) {
			this.procedures[this.getColumnCount()+columnIndex] = procedure;
		}
	}
	
	public void setProcedures(Procedure[] procedures) {
		this.procedures = procedures;
	}
	
	public Procedure[] getProcedures() {
		return this.procedures;
	}
	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		try {
			SqlOperation.delete(getTableName(), 
					Tools.makeArray(super.columnIdentifiers.toArray()),
					Tools.makeArray(super.dataVector.get(row).toArray()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		super.removeRow(row);
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		try {
			SqlOperation.update(getTableName(), 
					super.columnIdentifiers.get(column).toString(),
					aValue.toString(),
					Tools.makeArray(super.columnIdentifiers.toArray()),
					Tools.makeArray(super.dataVector.get(row).toArray()));
		} catch (SQLException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "修改不合法");
//			e.printStackTrace();
			return;
		}
		super.setValueAt(aValue, row, column);
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		if (procedures[column] != null ) {
			return procedures[column].set(super.getValueAt(row, column));
		} else {
			return super.getValueAt(row, column);
		}
	}
	
}