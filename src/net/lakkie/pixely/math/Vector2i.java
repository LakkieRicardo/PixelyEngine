package net.lakkie.pixely.math;

public class Vector2i
{

	public int x, y;

	public Vector2i()
	{
	}

	public Vector2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2i(Vector2i other)
	{
		this.x = other.x;
		this.y = other.y;
	}

	public Vector2i(int val)
	{
		this.x = val;
		this.y = val;
	}

	public Vector2i add(Vector2i other)
	{
		Vector2i result = new Vector2i(this);
		result.x += other.x;
		result.y += other.y;
		return result;
	}

	public Vector2i subtract(Vector2i other)
	{
		Vector2i result = new Vector2i(this);
		result.x -= other.x;
		result.y -= other.y;
		return result;
	}

	public Vector2i multiply(Vector2i other)
	{
		Vector2i result = new Vector2i(this);
		result.x *= other.x;
		result.y *= other.y;
		return result;
	}

	public Vector2i divide(Vector2i other)
	{
		Vector2i result = new Vector2i(this);
		result.x /= other.x;
		result.y /= other.y;
		return result;
	}

	public Vector2i modulus(Vector2i other)
	{
		Vector2i result = new Vector2i(this);
		result.x %= other.x;
		result.y %= other.y;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (!(obj instanceof Vector2i)) { return obj == this; }
		Vector2i other = (Vector2i) obj;
		return this.x == other.x && this.y == other.y;
	}

	public int length()
	{
		return Mathif.vecLength(this);
	}

	public String toString()
	{
		return String.format("(%s, %s)", x, y);
	}

	public Vector2i abs()
	{
		return Mathif.vecAbs(this);
	}

	public Vector2i scalar(int val)
	{
		return Mathif.vecScalar(this, val);
	}

	public Vector2i normalize()
	{
		return Mathif.vecNormalize(this);
	}

}
