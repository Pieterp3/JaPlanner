package util.math;

public class Misc {

	public static boolean isInteger(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}

	public static boolean randomBoolean() {
		return Math.random() < 0.5;
	}

	public static String formatDecimal(double x, int i) {
		return String.format("%." + i + "f", x);
	}

	public static int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	public static double getRandomNumber(double min, double max) {
		return (Math.random() * (max - min + 1)) + min;
	}

}
