package smallTools;

public class Tools {

	public static String[] makeArray(String ... str) {
		String[] re = new String[str.length];
		re = str.clone();
		return re;
	}
	
	public static String[] makeArray(Object[] str) {
		String[] re = new String[str.length];
		for (int i =0; i< str.length ;i++) {
			re[i] = (String)str[i];
		}
		return re;
	}
}
