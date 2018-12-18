package de.wepas.sort;

import java.util.Comparator;

import de.wepas.livegoals.LivegoalsTablerow;

public class SortLiveTablePunkteSpieltag implements Comparator<LivegoalsTablerow>
{
	@Override
	public int compare(LivegoalsTablerow arg0, LivegoalsTablerow arg1)
	{
		return arg1.getPunktespieltag() - arg0.getPunktespieltag();
	}
}