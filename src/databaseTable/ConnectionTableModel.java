package databaseTable;

import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

public class ConnectionTableModel extends MyTableModel {

	private MyTableModel[] key;
	
	public ConnectionTableModel(String tableName, String[] keyTableName, String[] keyColumnName) throws SQLException {
		super(tableName);
		this.setDisEdit(keyColumnName);
		key = new MyTableModel[keyColumnName.length];
		for (int i = 0; i < key.length; i++) {
			key[i] = new MyTableModel(keyTableName[i], keyColumnName[i]);
		}
	}
	
	private void setDisEdit(String[] keyColumnName) {
		int[] re = new int[keyColumnName.length];
		int i, j=0;
		boolean flag;
		for (String str : keyColumnName) {
			flag = false;
			for (i = 0; i < super.columnIdentifiers.size(); i++) {
				if (super.columnIdentifiers.get(i).toString().equals(str)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				re[j++] = i;
			}
		}
		super.setColumnsIsEditable(Arrays.copyOfRange(re, 0, j-1));
	}

	
	private boolean isKey(int column) {
		
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		if (condition) {
			super.columnIdentifiers.get(column).equals(key1)
		}
		return super.getValueAt(row, column);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
