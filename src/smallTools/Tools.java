package smallTools;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

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
	
	public static String[] makeArray(Object[] str, String ... str1) {
		ArrayList tmp = new ArrayList(Arrays.asList(str));
		tmp.addAll(Arrays.asList(str1));
		
		return makeArray(tmp.toArray());
		
	}
	
	public static String[] makeArray(String str, int num) {
		String[] re = new String[num];
		for (int i = 0; i < num; i++) {
			re[i] = str;
		}
		return re;
	}
	
	public static String[] makeArray(Vector<String> str) {
		String[] re = new String[str.size()];
		int i = 0;
		for (String string : str) {
			re[i++] = string.toString();
		}
		return re;
	}
}
