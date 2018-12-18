package sorts;

import java.util.Comparator;

import tables.StatistikTablerow;

public class SortStatistikTableName implements Comparator<StatistikTablerow>
{
	@Override
	public int compare(StatistikTablerow arg0, StatistikTablerow arg1)
	{
		if(arg0.getTipperId() == arg0.getTipperIdApril()) return 1;
		if(arg1.getTipperId() == arg1.getTipperIdApril()) return -1;
		
		return arg0.getSortname().compareTo(arg1.getSortname());
	}
}
