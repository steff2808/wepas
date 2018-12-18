package sorts;

import java.util.Comparator;

import tables.MainTablerow;

public class SortMainTablePunkteGesamt implements Comparator<MainTablerow>
{
	@Override
	public int compare(MainTablerow arg0, MainTablerow arg1)
	{
		if(arg0.getTipperId() == arg0.getTipperIdApril()) return 1;
		if(arg1.getTipperId() == arg1.getTipperIdApril()) return -1;
		
		return arg1.getPunktegesamt() - arg0.getPunktegesamt();
	}
}