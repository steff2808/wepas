package sorts;

import java.util.Comparator;

import tables.TreppchenTablerow;

public class SortTreppchenTableName implements Comparator<TreppchenTablerow>
{
	@Override
	public int compare(TreppchenTablerow arg0, TreppchenTablerow arg1)
	{
		return arg0.getSortname().compareTo(arg1.getSortname());
	}
}
