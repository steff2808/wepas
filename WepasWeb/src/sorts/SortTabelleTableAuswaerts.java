package sorts;

import java.util.Comparator;

import tables.TabelleTablerow;

public class SortTabelleTableAuswaerts implements Comparator<TabelleTablerow>
{
	@Override
	public int compare(TabelleTablerow arg0, TabelleTablerow arg1)
	{
		if(arg0.getpA() != arg1.getpA())
		{
			return arg1.getpA() - arg0.getpA();
		}
		if(arg0.gettGA() - arg0.gettKA() != arg1.gettGA() - arg1.gettKA())
		{
			return (arg1.gettGA() - arg1.gettKA()) - (arg0.gettGA() - arg0.gettKA());
		}
		return arg1.gettGA() - arg0.gettGA();
	}
}
