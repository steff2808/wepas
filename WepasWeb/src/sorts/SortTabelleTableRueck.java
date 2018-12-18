package sorts;

import java.util.Comparator;

import tables.TabelleTablerow;

public class SortTabelleTableRueck implements Comparator<TabelleTablerow>
{
	@Override
	public int compare(TabelleTablerow arg0, TabelleTablerow arg1)
	{
		if(arg0.getpRr() != arg1.getpRr())
		{
			return arg1.getpRr() - arg0.getpRr();
		}
		if(arg0.gettGRr() - arg0.gettKRr() != arg1.gettGRr() - arg1.gettKRr())
		{
			return (arg1.gettGRr() - arg1.gettKRr()) - (arg0.gettGRr() - arg0.gettKRr());
		}
		return arg1.gettGRr() - arg0.gettGRr();
	}
}
