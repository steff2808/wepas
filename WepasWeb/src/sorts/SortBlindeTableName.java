package sorts;

import java.util.Comparator;

import tables.BlindeTablerow;
public class SortBlindeTableName implements Comparator<BlindeTablerow>
{
	@Override
	public int compare(BlindeTablerow arg0, BlindeTablerow arg1)
	{
		return arg0.getSortname().compareTo(arg1.getSortname());
	}
}
