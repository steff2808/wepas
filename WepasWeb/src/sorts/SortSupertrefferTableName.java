package sorts;

import java.util.Comparator;

import tables.SupertrefferTablerow;

public class SortSupertrefferTableName implements Comparator<SupertrefferTablerow>
{
	@Override
	public int compare(SupertrefferTablerow arg0, SupertrefferTablerow arg1)
	{
		return arg0.getSortname().compareTo(arg1.getSortname());
	}
}
