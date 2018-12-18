package sorts;

import java.util.Comparator;

import de.wepas.jpa.EnWette;

public class SortWettePunkte implements Comparator<EnWette>
{
	@Override
	public int compare(EnWette arg0, EnWette arg1)
	{
		return arg1.getAtWettePunkte() - arg0.getAtWettePunkte();
	}
}