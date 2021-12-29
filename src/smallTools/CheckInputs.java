package smallTools;

public class CheckInputs {

	public static boolean checkId(String id) {
		return id.matches("\\d{12}");
	}
	
	public static boolean checkName(String name) {
		return !name.matches("");
	}

	public static boolean checkGrade(String grade) {
//		int grade_int = Integer.parseInt(grade);
		int grade_int = 10;
		boolean re =false;
		if (grade_int >=0 && grade_int <=100) {
			re = true;
		}
		return re;
	}
}
