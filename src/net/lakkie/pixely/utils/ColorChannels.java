package net.lakkie.pixely.utils;

import java.awt.Color;

import net.lakkie.pixely.math.Mathif;

public class ColorChannels
{

	private ColorChannels()
	{
	}

	public static int getA(int argb)
	{
		return (argb & 0xff000000) >> 24;
	}

	public static int getR(int argb)
	{
		return (argb & 0x00ff0000) >> 16;
	}

	public static int getG(int argb)
	{
		return (argb & 0x0000ff00) >> 8;
	}

	public static int getB(int argb)
	{
		return (argb & 0x000000ff);
	}

	public static int assembleARGB(int a, int r, int g, int b)
	{
		return (a << 24) | (r << 16) | (g << 8) | (b);
	}

	public static int multiplyColor(int argb, float multiplier, boolean multiplyAlpha)
	{
		float a = getA(argb);
		float r = getR(argb);
		float g = getG(argb);
		float b = getB(argb);
		if (multiplyAlpha)
		{
			a *= multiplier;
		}
		r *= multiplier;
		g *= multiplier;
		b *= multiplier;
		a = Mathif.clamp(a, 0.0f, 255.0f);
		r = Mathif.clamp(r, 0.0f, 255.0f);
		g = Mathif.clamp(g, 0.0f, 255.0f);
		b = Mathif.clamp(b, 0.0f, 255.0f);
		return assembleARGB((int) a, (int) r, (int) g, (int) b);
	}

	/**
	 * 
	 * Changes brightness by adding.
	 * 
	 * @param argb
	 *            The input color in ARGB format.
	 * @param brightness
	 *            The brightness value from 0 to 1
	 * @return The ARGB color result with adjusted brightness
	 */
	public static int brightnessAdd(int argb, float brightness)
	{
		int a = getA(argb);
		float[] hsb = new float[3];
		Color.RGBtoHSB(getR(argb), getG(argb), getB(argb), hsb);
		hsb[2] += brightness;
		int result = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		return result & ((a << 24) | 0x00ffffff);
	}

	public static int multiplyColor(int argb, int amount)
	{
		int r = getR(argb);
		int g = getG(argb);
		int b = getB(argb);
		r *= amount;
		g *= amount;
		b *= amount;
		r = Mathif.clamp(r, 0, 255);
		g = Mathif.clamp(g, 0, 255);
		b = Mathif.clamp(b, 0, 255);
		return assembleARGB(getA(argb), r, g, b);
	}

	public static int addColor(int argb, int amount)
	{
		int r = getR(argb);
		int g = getG(argb);
		int b = getB(argb);
		r += amount;
		g += amount;
		b += amount;
		r = Mathif.clamp(r, 0, 255);
		g = Mathif.clamp(g, 0, 255);
		b = Mathif.clamp(b, 0, 255);
		return assembleARGB(getA(argb), r, g, b);
	}

}
