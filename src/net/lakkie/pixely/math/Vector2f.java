package net.lakkie.pixely.math;

/**
 * 
 * A copy and paste of {@link Vector2i} but with floats instead of integers.
 * 
 * @author Diego
 *
 */
public class Vector2f
{

	public float x, y;

	public Vector2f()
	{
	}

	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2f(Vector2f other)
	{
		this.x = other.x;
		this.y = other.y;
	}

	public Vector2f(float val)
	{
		this.x = val;
		this.y = val;
	}

	public Vector2f add(Vector2f other)
	{
		Vector2f result = new Vector2f(this);
		result.x += other.x;
		result.y += other.y;
		return result;
	}

	public Vector2f subtract(Vector2f other)
	{
		Vector2f result = new Vector2f(this);
		result.x -= other.x;
		result.y -= other.y;
		return result;
	}

	public Vector2f multiply(Vector2f other)
	{
		Vector2f result = new Vector2f(this);
		result.x *= other.x;
		result.y *= other.y;
		return result;
	}

	public Vector2f divide(Vector2f other)
	{
		Vector2f result = new Vector2f(this);
		result.x /= other.x;
		result.y /= other.y;
		return result;
	}

	public Vector2f modulus(Vector2f other)
	{
		Vector2f result = new Vector2f(this);
		result.x %= other.x;
		result.y %= other.y;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (!(obj instanceof Vector2f)) { return obj == this; }
		Vector2f other = (Vector2f) obj;
		return Mathif.flcmp(this.x, other.x) && Mathif.flcmp(this.y, other.y);
	}

	public float length()
	{
		return Mathif.vecLength(this);
	}

	public String toString()
	{
		return String.format("(%s, %s)", x, y);
	}

	public Vector2f abs()
	{
		return Mathif.vecAbs(this);
	}

	public Vector2f normalize()
	{
		return Mathif.vecNormalize(this);
	}

	public Vector2i ceil()
	{
		return Mathif.vecCeil(this);
	}

	public Vector2i floor()
	{
		return Mathif.vecFloor(this);
	}

	public Vector2f scalar(float val)
	{
		return Mathif.vecScalar(this, val);
	}

}
