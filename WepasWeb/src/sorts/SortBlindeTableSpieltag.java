package sorts;

import java.util.Comparator;

import tables.BlindeTablerow;

public class SortBlindeTableSpieltag implements Comparator<BlindeTablerow>
{
	@Override
	public int compare(BlindeTablerow arg0, BlindeTablerow arg1)
	{
		return arg1.getSpieltag() - arg0.getSpieltag();
	}
}
