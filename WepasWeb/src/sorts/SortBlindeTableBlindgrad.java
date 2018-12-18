package sorts;

import java.util.Comparator;

import tables.BlindeTablerow;

public class SortBlindeTableBlindgrad implements Comparator<BlindeTablerow>
{
	@Override
	public int compare(BlindeTablerow arg0, BlindeTablerow arg1)
	{
		return arg1.getTipp().getAtTippBlind() - arg0.getTipp().getAtTippBlind();
	}
}
