package databaseTable;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import smallTools.Tools;
import sql.SqlOperation;

public class MyTableModel extends DefaultTableModel {
	
	private int[] disEdit;
	private String tableName = null;
	public MyTableModel(Vector<?> columnNames, int rowCount, int[] disEdit) {
		super(columnNames,rowCount);
		this.disEdit = disEdit;
	}
	
	public MyTableModel(Vector<Vector> dataVector, Vector<String> columnNames) {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		for (int col : this.disEdit) {
			if (columnIndex == col) {
				return false;
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
			e.printStackTrace();
			return;
		}
		super.setValueAt(aValue, row, column);
	}
	
	
}