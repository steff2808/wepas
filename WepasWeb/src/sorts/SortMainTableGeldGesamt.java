package sorts;

import java.util.Comparator;

import tables.MainTablerow;

public class SortMainTableGeldGesamt implements Comparator<MainTablerow>
{
	@Override
	public int compare(MainTablerow arg0, MainTablerow arg1)
	{
		double geld1 = arg1.getBonus().doubleValue() + arg1.getGeldgesamt().doubleValue();
		double geld0 = arg0.getBonus().doubleValue() + arg0.getGeldgesamt().doubleValue();
		if(geld1 > geld0)
		{
			return 1;
		}
		if(geld1 < geld0)
		{
			return -1;
		}
		return 0;
	}
}