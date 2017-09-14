package net.lakkie.pixely.graphics.shader.i;

import net.lakkie.pixely.graphics.shader.ShaderProgram;

public interface IShaderComponentBase
{

	int[] getCanvas();

	void assignCanvas(int[] pixels);

	ShaderProgram getShaderProgram();

	void receiveShader(ShaderProgram program);

	void init();

}
