package sorts;

import java.util.Comparator;

import tables.TabelleTablerow;

public class SortTabelleTableGesamt implements Comparator<TabelleTablerow>
{
	@Override
	public int compare(TabelleTablerow arg0, TabelleTablerow arg1)
	{
		if(arg0.getP() != arg1.getP())
		{
			return arg1.getP() - arg0.getP();
		}
		if(arg0.gettG() - arg0.gettK() != arg1.gettG() - arg1.gettK())
		{
			return (arg1.gettG() - arg1.gettK()) - (arg0.gettG() - arg0.gettK());
		}
		return arg1.gettG() - arg0.gettG();
	}
}
