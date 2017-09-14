package net.lakkie.pixely.graphics.tex.loadBuffered;

import java.nio.IntBuffer;

public class IntBufferExtractor
{

	private IntBuffer buffer;

	public IntBufferExtractor(IntBuffer buffer)
	{
		this.buffer = buffer;
	}

	public int readAndMoveInteger()
	{
		if (!isSafeToRead(1))
		{
			resetRead();
		}
		assert isSafeToRead(1);
		return this.buffer.get();
	}

	public int getRemainingIntegers()
	{
		return this.buffer.remaining();
	}

	public int[] readAndMoveIntegerArray(int length)
	{
		if (!isSafeToRead(length))
		{
			resetRead();
		}
		assert isSafeToRead(length);
		int[] result = new int[length];
		for (int i = 0; i < length; i++)
		{
			if (!isSafeToRead(length - i))
			{
				resetRead();
			}
			result[i] = readAndMoveInteger();
		}
		return result;
	}

	public void resetRead()
	{
		this.buffer.position(0);
	}

	public IntBuffer getBuffer()
	{
		return buffer;
	}

	public boolean isSafeToRead(int length)
	{
		return getRemainingIntegers() >= length;
	}

}
