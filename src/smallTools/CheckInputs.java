package smallTools;

public class CheckInputs {

	public static boolean checkId(String id) {
		return id.matches("\\d{8}");
	}
	
	public static boolean checkIsEmpty(String name) {
		return !name.matches("");
	}

	public static boolean checkGrade(String grade) {
		boolean re =false;
		if (!grade.equals("")) {
			int grade_int = Integer.parseInt(grade);
			if (grade_int >=0 && grade_int <=100) {
				re = true;
			}
		}
		return re;
	}
}
