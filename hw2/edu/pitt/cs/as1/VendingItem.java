package edu.pitt.cs.as1;

public final class VendingItem implements VendingItemInterface
{
	private final double price;
	private final String name;

	VendingItem(double aPrice, String aName)
	{
		price = aPrice;
		name = aName;
	}

	public double getPrice()
	{
		return price;
	}

	public String getName()
	{
		return name;
	}

}