package tables;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sorts.SortHighscoreTableGewinn;
import sorts.SortHighscoreTableName;
import sorts.SortHighscoreTablePunkte;
import sorts.SortHighscoreTableSpieltag;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.connector.WetteException;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnWette;

public class HighscoreTable
{
	private static Log log = LogFactory.getLog(HighscoreTable.class);

	Vector<HighscoreTablerow> rows;
	HighscoreTablehead1 head1;
	HighscoreTablehead2 head2;

	public HighscoreTable(EnTipper user, WetteBeanRemote wetteStateless, TipperBeanRemote tipperStateless, SpieltagBeanRemote spieltagStateless)
	{
		super();
		this.rows = new Vector<HighscoreTablerow>(50);
		this.head1 = new HighscoreTablehead1();
		this.head2 = new HighscoreTablehead2();
		Vector<EnWette> wetten = null;
		try
		{
			EnWette[] wettearray = wetteStateless.getSieger();
			wetten = new Vector<EnWette>(wettearray.length);
			for (int i = 0; i < wettearray.length; i++)
			{
				wetten.add(wettearray[i]);
			}
			Iterator<EnWette> iter1 = wetten.iterator();
			while (iter1.hasNext())
			{
				this.rows.add(new HighscoreTablerow(user, iter1.next(), tipperStateless, spieltagStateless));
			}
		}
		catch (WetteException e)
		{
			log.error("TipperException Zeile 56 " + e.getMessage());
		} 
	}
	
	public String getHtmlRow()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append(this.head1.getHtmlRow());
		sb.append(this.head2.getHtmlRow());
		Iterator<HighscoreTablerow> iter1 = this.rows.iterator();
		while (iter1.hasNext())
		{
			sb.append(iter1.next().getHtmlRow());
		}
		return sb.toString();
	}

	public void sortName()
	{
		Collections.sort(this.rows, new SortHighscoreTableName());
	}

	public void sortSpieltag()
	{
		Collections.sort(this.rows, new SortHighscoreTableSpieltag());
	}

	public void sortPunkte()
	{
		Collections.sort(this.rows, new SortHighscoreTablePunkte());
	}

	public void sortGewinn()
	{
		Collections.sort(this.rows, new SortHighscoreTableGewinn());
	}
}
