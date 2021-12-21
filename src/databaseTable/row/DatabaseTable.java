package databaseTable.row;

public abstract class DatabaseTable {

	private String id;
	
	public  String[] getTableHeader() {
		return null;
	};
	
	protected static String[] makeArray(String ... str) {
		String[] re = new String[str.length];
		re = str.clone();
		return re;
	}
}
