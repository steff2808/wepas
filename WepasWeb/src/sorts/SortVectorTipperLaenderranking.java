package sorts;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import de.wepas.jpa.EnTipper;

public class SortVectorTipperLaenderranking implements Comparator<Vector<EnTipper>>
{
	@Override
	public int compare(Vector<EnTipper> arg0, Vector<EnTipper> arg1)
	{
		int punkte0 = 0;
		int punkte1 = 0;
		Iterator<EnTipper> iter0 = arg0.iterator();
		while(iter0.hasNext())
		{
			punkte0 = punkte0 + iter0.next().getAtTipperPunkte();
		}
		Iterator<EnTipper> iter1 = arg1.iterator();
		while(iter1.hasNext())
		{
			punkte1 = punkte1 + iter1.next().getAtTipperPunkte();
		}
		double quotient0 = (double)punkte0 / (double)arg0.size();
		double quotient1 = (double)punkte1 / (double)arg1.size();
		if(quotient1 > quotient0)
		{
			return 1;
		}
		if(quotient1 < quotient0)
		{
			return -1;
		}
		return 0;
	}
}