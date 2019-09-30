package edu.pitt.cs.as1;

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
		shelf = (T[][][]) new VendingItem[rows][columns][quantity];
	}

	public T get(int row, int col, int pos)
	{
		return shelf[row][col][pos];
	}

	public String getName(int row, int col, int pos)
	{
		VendingItem item = (VendingItem) shelf[row][col][pos];
		String name = item.getName();
		return name;
	}

	public void add(int row, int column, int position, T object)
	{
		if(row<rows && column < columns && position < quantity)
		{
			shelf[row][column][position] = object;
		}
	}

	public void remove(int row, int column)
	{
		for(int i = 0; i<quantity-1;i++){
			shelf[row][column][i] = shelf[row][column][i+1];
		}
		shelf[row][column][quantity-1] = null;

	}

	public boolean contains(T entry)
	{
		if(entry == null)
		{
			return false;
			//throw new IllegalArgumentException("entry to contains must not be null");
		}
		for (int i = 0; i <rows;i++){
			for(int j = 0;j<columns;j++){
				for(int k =0;k<quantity;k++){
					if(shelf[i][j][k] == null){
						continue;
					}
					if(shelf[i][j][k].equals(entry))
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isEmpty(int row, int column, int position)
	{
		if(shelf[row][column][position] == null)
		{
			return true;
		}
		return false;
	}

	public T[][][] toArray()
	{
		@SuppressWarnings("unchecked")
		T[][][] result = (T[][][])new Object[rows][columns][quantity];
		for (int i = 0; i <rows;i++){
			for(int j = 0;j<columns;j++){
				for(int k =0;k<quantity;k++)
				{
					result[i][j][k] = shelf[i][j][k];
				}
			}
		}
		return result;

	}


}