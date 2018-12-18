package sorts;

import java.util.Comparator;

import tables.TabelleTablerow;

public class SortTabelleTableHin implements Comparator<TabelleTablerow>
{
	@Override
	public int compare(TabelleTablerow arg0, TabelleTablerow arg1)
	{
		if(arg0.getpHr() != arg1.getpHr())
		{
			return arg1.getpHr() - arg0.getpHr();
		}
		if(arg0.gettGHr() - arg0.gettKHr() != arg1.gettGHr() - arg1.gettKHr())
		{
			return (arg1.gettGHr() - arg1.gettKHr()) - (arg0.gettGHr() - arg0.gettKHr());
		}
		return arg1.gettGHr() - arg0.gettGHr();
	}
}
