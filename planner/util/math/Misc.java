package util.math;

public class Misc {
	
	public static boolean isInteger(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) { }
		return false;
	}

}
