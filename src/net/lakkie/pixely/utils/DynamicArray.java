package net.lakkie.pixely.utils;

/**
 * A thread-safe array that can be resized.
 * 
 * @author Diego
 *
 * @param <T>
 *            The type of the array's values
 */
public class DynamicArray<T>
{

	public volatile T[] value;
	private IArrayCreator<T> creator;

	public DynamicArray(IArrayCreator<T> creator, int size)
	{
		this.creator = creator;
		this.value = this.creator.create(size);
	}

	/**
	 * Changes the array size to a new size
	 * 
	 * @param newSize
	 *            The new size to change the array to.
	 */
	public synchronized void resize(int newSize)
	{
		assert (newSize != 0) : "Resizing array by 0!";
		T[] newArray = this.creator.create(newSize);
		System.arraycopy(this.value, 0, newArray, 0, newSize);
		this.value = newArray;
	}

	/**
	 * Increases the size of the array by size
	 * 
	 * @param size
	 *            The size to increase it by
	 */
	public void grow(int size)
	{
		this.resize(this.value.length + size);
	}

	/**
	 * Decreases the size of the array by size
	 * 
	 * @param size
	 *            The size to decrease it by
	 */
	public void shrink(int size)
	{
		this.resize(this.value.length - size);
	}

	public IArrayCreator<T> getCreator()
	{
		return creator;
	}

	public void setCreator(IArrayCreator<T> creator)
	{
		this.creator = creator;
	}

	public interface IArrayCreator<T>
	{

		T[] create(int size);

	}

}
