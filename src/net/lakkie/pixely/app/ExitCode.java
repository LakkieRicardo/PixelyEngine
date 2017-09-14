package net.lakkie.pixely.app;

public enum ExitCode
{

	SUCCESS(0), ERROR(1), RESOURCE_LOAD_FAILURE(2), WARNING(3), NETWORK_ERROR(4);

	private final int code;

	private ExitCode(int code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}

	public String toString()
	{
		return this.name().toLowerCase().replaceAll("_", " ") + "(" + getCode() + ")";
	}

}
