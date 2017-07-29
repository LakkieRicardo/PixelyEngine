package net.lakkie.pixely.utils;

public class PixelyVersion {

	public static String getVersionType() {
		return "a";
	}

	public static String getVersionMajor() {
		return "1";
	}

	public static String getVersionMinor() {
		return "0";
	}

	public static String getBuildNumber() {
		return "1";
	}
	
	public static boolean isDevelopmentBuild() {
		return true;
	}

	public static String getVersion() {
		return getVersionType() + getVersionMajor() + "." + getVersionMinor() + " " + (isDevelopmentBuild() ? "Development " : "") + "Build " + getBuildNumber();
	}

}
