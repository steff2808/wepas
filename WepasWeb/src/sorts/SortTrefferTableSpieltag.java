package sorts;

import java.util.Comparator;

import tables.TrefferTablerow;

public class SortTrefferTableSpieltag implements Comparator<TrefferTablerow>
{
	@Override
	public int compare(TrefferTablerow arg0, TrefferTablerow arg1)
	{
		return arg1.getSpieltag() - arg0.getSpieltag();
	}
}
