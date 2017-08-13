package net.lakkie.pixely.utils;

public class Mathi {

	public static int clamp(int value, int min, int max) {
		if (value <= min) {
			return min;
		}
		if (value >= max) {
			return max;
		}
		return value;
	}
	
	public static float clamp(float value, float min, float max) {
		if (value <= min) {
			return min;
		}
		if (value >= max) {
			return max;
		}
		return value;
	}
	
}
