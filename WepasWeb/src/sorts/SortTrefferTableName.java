package sorts;

import java.util.Comparator;

import tables.TrefferTablerow;
public class SortTrefferTableName implements Comparator<TrefferTablerow>
{
	@Override
	public int compare(TrefferTablerow arg0, TrefferTablerow arg1)
	{
		return arg0.getSortname().compareTo(arg1.getSortname());
	}
}
