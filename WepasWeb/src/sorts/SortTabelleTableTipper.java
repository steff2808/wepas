package sorts;

import java.util.Comparator;

import tables.TabelleTablerow;

public class SortTabelleTableTipper implements Comparator<TabelleTablerow>
{
	@Override
	public int compare(TabelleTablerow arg0, TabelleTablerow arg1)
	{
		if(arg0.getpT() != arg1.getpT())
		{
			return arg1.getpT() - arg0.getpT();
		}
		if(arg0.gettGT() - arg0.gettKT() != arg1.gettGT() - arg1.gettKT())
		{
			return (arg1.gettGT() - arg1.gettKT()) - (arg0.gettGT() - arg0.gettKT());
		}
		return arg1.gettGT() - arg0.gettGT();
	}
}
