package sorts;

import java.util.Comparator;

import tables.HighscoreTablerow;

public class SortHighscoreTableName implements Comparator<HighscoreTablerow>
{
	@Override
	public int compare(HighscoreTablerow arg0, HighscoreTablerow arg1)
	{
		return arg0.getSortname().compareTo(arg1.getSortname());
	}
}
