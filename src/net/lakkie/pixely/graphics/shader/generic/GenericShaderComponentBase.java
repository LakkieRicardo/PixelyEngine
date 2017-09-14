package net.lakkie.pixely.graphics.shader.generic;

import net.lakkie.pixely.graphics.shader.ShaderProgram;
import net.lakkie.pixely.graphics.shader.i.IShaderComponentBase;

public class GenericShaderComponentBase implements IShaderComponentBase
{

	protected int[] pixels;
	protected ShaderProgram shader;

	public int[] getCanvas()
	{
		return this.pixels;
	}

	public void assignCanvas(int[] pixels)
	{
		this.pixels = pixels;
	}

	public ShaderProgram getShaderProgram()
	{
		return this.shader;
	}

	public void receiveShader(ShaderProgram program)
	{
		this.shader = program;
	}

	public void init()
	{
	}

}
