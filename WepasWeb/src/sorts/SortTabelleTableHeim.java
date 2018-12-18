package sorts;

import java.util.Comparator;

import tables.TabelleTablerow;

public class SortTabelleTableHeim implements Comparator<TabelleTablerow>
{
	@Override
	public int compare(TabelleTablerow arg0, TabelleTablerow arg1)
	{
		if(arg0.getpH() != arg1.getpH())
		{
			return arg1.getpH() - arg0.getpH();
		}
		if(arg0.gettGH() - arg0.gettKH() != arg1.gettGH() - arg1.gettKH())
		{
			return (arg1.gettGH() - arg1.gettKH()) - (arg0.gettGH() - arg0.gettKH());
		}
		return arg1.gettGH() - arg0.gettGH();
	}
}
