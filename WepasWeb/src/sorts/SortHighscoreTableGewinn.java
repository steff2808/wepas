package sorts;

import java.util.Comparator;

import tables.HighscoreTablerow;

public class SortHighscoreTableGewinn implements Comparator<HighscoreTablerow>
{
	@Override
	public int compare(HighscoreTablerow arg0, HighscoreTablerow arg1)
	{
		return (int) arg1.getGewinn() - (int) arg0.getGewinn();
	}
}
