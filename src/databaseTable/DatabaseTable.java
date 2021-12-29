package databaseTable;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DatabaseTable extends AbstractTableModel {

	private ArrayList columnIdentifiers;
	private ArrayList<ArrayList> dataArrayList;
	private boolean[] isEdit;
	protected EventListenerList listenerList = new EventListenerList();
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dataArrayList.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnIdentifiers.size();
	}

	public String getColumnName(int columnIndex) {
		Object id = null;
		// TODO Auto-generated method stub
		 if (columnIndex < columnIdentifiers.size() && (columnIndex >= 0)) {
			 id = columnIdentifiers.get(columnIndex);
		 }
		 return (id == null) ? "abcdefghijklnmopqrstuvwxyz".substring(0, columnIndex)
				                             : id.toString();
	}

	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return isEdit[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Object result = null;
		result = this.dataArrayList.get(rowIndex).get(columnIndex);
		return result;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		dataArrayList.get(rowIndex).set(columnIndex, aValue);
	}

//	public void addTableModelListener(TableModelListener l) {
//		// TODO Auto-generated method stub
//		listenerList.add(TableModelListener.class, l);
//		
//	}

//	public void removeTableModelListener(TableModelListener l) {
//		// TODO Auto-generated method stub
//		listenerList.remove(TableModelListener.class, l);
//	}
	
}
