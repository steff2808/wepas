package sorts;

import java.util.Comparator;

import tables.TreppchenTablerow;

public class SortTreppchenTableSpieltag implements Comparator<TreppchenTablerow>
{
	@Override
	public int compare(TreppchenTablerow arg0, TreppchenTablerow arg1)
	{
		return arg1.getSpieltag() - arg0.getSpieltag();
	}
}
