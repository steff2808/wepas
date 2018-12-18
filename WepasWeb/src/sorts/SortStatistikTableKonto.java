package sorts;

import java.util.Comparator;

import tables.StatistikTablerow;

public class SortStatistikTableKonto implements Comparator<StatistikTablerow>
{
	@Override
	public int compare(StatistikTablerow arg0, StatistikTablerow arg1)
	{
		if(arg0.getTipperId() == arg0.getTipperIdApril()) return 1;
		if(arg1.getTipperId() == arg1.getTipperIdApril()) return -1;

		return (int) arg1.getKonto() - (int) arg0.getKonto();
	}
}
