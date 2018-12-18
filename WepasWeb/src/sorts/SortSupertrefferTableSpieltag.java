package sorts;

import java.util.Comparator;

import tables.SupertrefferTablerow;

public class SortSupertrefferTableSpieltag implements Comparator<SupertrefferTablerow>
{
	@Override
	public int compare(SupertrefferTablerow arg0, SupertrefferTablerow arg1)
	{
		return arg1.getSpieltag() - arg0.getSpieltag();
	}
}
