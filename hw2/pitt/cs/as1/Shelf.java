public class Shelf<T> implements ShelfInterface<T>
{
	private T[][][] shelf;
	private final int rows;
	private final int columns;
	private final int quantity;


	public Shelf(int r, int c, int q)
	{
		rows = r;
		columns = c;
		quantity = q;
		shelf = [rows][columns][quantity];
	}

	public T get(int row, int col, int pos)
	{
		return shelf[row][col][pos];

	}

	public String getName(int row, int col, int pos)
	{
		return shelf[row][col][pos].getName();

	}

	public void add(int row, int column, int position, T o)
	{

	}

	public void remove(int row, int column)
	{

	}

	public boolean contains(T entry)
	{

	}

	public boolean isEmpty(int row, int column, int position)
	{

	}

	public T[][][] toArray()
	{

	}


}