package sorts;

import java.util.Comparator;

import tables.MainTablerow;

public class SortMainTableKonto implements Comparator<MainTablerow>
{
	@Override
	public int compare(MainTablerow arg0, MainTablerow arg1)
	{
		if(arg0.getTipperId() == arg0.getTipperIdApril()) return 1;
		if(arg1.getTipperId() == arg1.getTipperIdApril()) return -1;
		
		if(arg1.getGeldgesamt().doubleValue() > arg0.getGeldgesamt().doubleValue())
		{
			return 1;
		}
		if(arg1.getGeldgesamt().doubleValue() < arg0.getGeldgesamt().doubleValue())
		{
			return -1;
		}
		return 0;
	}
}