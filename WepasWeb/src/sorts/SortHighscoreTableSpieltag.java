package sorts;

import java.util.Comparator;

import tables.HighscoreTablerow;

public class SortHighscoreTableSpieltag implements Comparator<HighscoreTablerow>
{
	@Override
	public int compare(HighscoreTablerow arg0, HighscoreTablerow arg1)
	{
		return arg1.getSpieltag() - arg0.getSpieltag();
	}
}
