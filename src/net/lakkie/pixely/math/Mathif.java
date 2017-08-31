package net.lakkie.pixely.math;

/**
 * 
 * This class concerns all basic operations in math, including vector
 * operations.<br>
 * The <code>if</code> in <code>Mathif</code> stands for integer, float.
 * Primarily, integer operations are for more lower level operations in the
 * engine, that is, pixel operations. Floats are used for physics calculations
 * to be more precise, and for a multitude more of advantages, I.E.
 * <ul>
 * <li>Options to make speeds less than 1 pixel/second</li>
 * <li>Precise positions</li>
 * <li></li>
 * <li></li>
 * </ul>
 * 
 * @author Diego
 *
 */
public class Mathif {

	/**
	 * The minimum difference 2 floats can be before they are no longer equal
	 */
	public static final float MIN_FLOAT_IMPRECISION = 1.0e-6f;

	private Mathif() {
	}

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

	public static Vector2f vecConvertToFloat(Vector2i orig) {
		return new Vector2f(orig.x, orig.y);
	}

	/**
	 * Makes a new {@link Vector2i} and by floor <code>vec</code>'s values
	 * 
	 * @param vec
	 *            The vector to be floored.
	 * @return A new floored vector.
	 */
	public static Vector2i vecFloor(Vector2f vec) {
		return new Vector2i((int) Math.floor(vec.x), (int) Math.floor(vec.y));
	}

	/**
	 * Makes a new {@link Vector2i} and by ceil <code>vec</code>'s values
	 * 
	 * @param vec
	 *            The vector to be ceiled.
	 * @return A new ceiled vector.
	 */
	public static Vector2i vecCeil(Vector2f vec) {
		return new Vector2i((int) Math.ceil(vec.x), (int) Math.ceil(vec.y));
	}

	public static Vector2f vecAbs(Vector2f a) {
		return new Vector2f(Math.abs(a.x), Math.abs(a.y));
	}

	public static Vector2i vecAbs(Vector2i a) {
		return new Vector2i(Math.abs(a.x), Math.abs(a.y));
	}

	/**
	 * Creates a new vector and multiplies it by the specified vector.
	 * 
	 * @param a
	 *            The vector's values to be used(not modified)
	 * @param value
	 *            The scalar value
	 * @return The scalar vector
	 */
	public static Vector2f vecScalar(Vector2f a, float value) {
		return a.multiply(new Vector2f(value));
	}

	public static Vector2i vecScalar(Vector2i a, int value) {
		return a.multiply(new Vector2i(value));
	}

	public static float vecLength(Vector2f a) {
		return a.x + a.y;
	}

	public static int vecLength(Vector2i a) {
		return a.x + a.y;
	}

	public static Vector2f vecNormalize(Vector2f a) {
		float len = vecLength(a);
		float nx = a.x / len;
		float ny = a.y / len;
		return new Vector2f(nx, ny);
	}

	public static Vector2i vecNormalize(Vector2i a) {
		int len = vecLength(a);
		int nx = a.x / len;
		int ny = a.y / len;
		return new Vector2i(nx, ny);
	}

	/**
	 * Compares 2 floats accounting for floating point imprecision.
	 * 
	 * @param a
	 *            First float
	 * @param b
	 *            Second float
	 * @return If these 2 floats' differences were less than
	 *         {@link VectorMath#MIN_FLOAT_IMPRECISION}
	 */
	public static boolean flcmp(float a, float b) {
		return Math.abs(a - b) < MIN_FLOAT_IMPRECISION;
	}

	/**
	 * This function rounds a float to floor or ceil depending on the decimal.
	 * 
	 * @param a
	 *            The input float
	 * @return The rounded float
	 */
	public static float halfRound(float a) {
		float decimal = (float) (a - Math.floor(a));
		if (decimal < 0.5f)
			return (float) Math.floor(a);
		else
			return (float) Math.ceil(a);
	}

	/**
	 * Converts the float result from <code>halfRound</code> to an <code>int</code>
	 * 
	 * @see {@link Mathif#halfRound(float)}
	 */
	public static int halfRoundInt(float a) {
		return (int) halfRound(a);
	}

}
