package net.lakkie.pixely.app;

public enum ExitCode {
	
	// TODO: Add more exit codes
	
	SUCCESS(0), ERROR(1);
	
	private final int code;
	
	private ExitCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
