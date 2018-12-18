package sorts;

import java.util.Comparator;

import tables.TreppchenTablerow;

public class SortTreppchenTableGewinn implements Comparator<TreppchenTablerow>
{
	@Override
	public int compare(TreppchenTablerow arg0, TreppchenTablerow arg1)
	{
		return (int) arg1.getGewinn() - (int) arg0.getGewinn();
	}
}
