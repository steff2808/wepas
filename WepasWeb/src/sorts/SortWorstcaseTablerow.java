package sorts;

import java.util.Comparator;

import tables.WorstcaseTablerow;


public class SortWorstcaseTablerow implements Comparator<WorstcaseTablerow>
{

	@Override
	public int compare(WorstcaseTablerow arg0, WorstcaseTablerow arg1)
	{
		return arg1.getPunkteGesamt() - arg0.getPunkteGesamt();
	}
}
