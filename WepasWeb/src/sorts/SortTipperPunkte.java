package sorts;

import java.util.Comparator;

import de.wepas.jpa.EnTipper;

public class SortTipperPunkte implements Comparator<EnTipper>
{
	@Override
	public int compare(EnTipper arg0, EnTipper arg1)
	{
		return arg1.getAtTipperPunkte() - arg0.getAtTipperPunkte();
	}
}