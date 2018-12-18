package sorts;

import java.util.Comparator;

import tables.BestcaseTablerow;


public class SortBestcaseTablerow implements Comparator<BestcaseTablerow>
{

	@Override
	public int compare(BestcaseTablerow arg0, BestcaseTablerow arg1)
	{
		return arg1.getPunkteGesamt() - arg0.getPunkteGesamt();
	}
}
