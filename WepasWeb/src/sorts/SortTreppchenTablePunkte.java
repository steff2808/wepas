package sorts;

import java.util.Comparator;

import tables.TreppchenTablerow;

public class SortTreppchenTablePunkte implements Comparator<TreppchenTablerow>
{
	@Override
	public int compare(TreppchenTablerow arg0, TreppchenTablerow arg1)
	{
		return arg1.getPunkte() - arg0.getPunkte();
	}
}
