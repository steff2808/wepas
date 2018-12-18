package tables;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sorts.SortTreppchenTableGewinn;
import sorts.SortTreppchenTableName;
import sorts.SortTreppchenTablePunkte;
import sorts.SortTreppchenTableSpieltag;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.connector.WetteException;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnWette;

public class TreppchenTable
{
	private static Log log = LogFactory.getLog(TreppchenTable.class);

	Vector<TreppchenTablerow> rows;
	TreppchenTablehead1 head1;
	TreppchenTablehead2 head2;

	public TreppchenTable(EnTipper user, WetteBeanRemote wetteStateless, TipperBeanRemote tipperStateless, SpieltagBeanRemote spieltagStateless)
	{
		super();
		this.rows = new Vector<TreppchenTablerow>(50);
		this.head1 = new TreppchenTablehead1();
		this.head2 = new TreppchenTablehead2();
		Vector<EnWette> wetten = null;
		try
		{
			EnWette[] wettearray = wetteStateless.getGewinner();
			wetten = new Vector<EnWette>(wettearray.length);
			for (int i = 0; i < wettearray.length; i++)
			{
				wetten.add(wettearray[i]);
			}
			Iterator<EnWette> iter1 = wetten.iterator();
			while (iter1.hasNext())
			{
				this.rows.add(new TreppchenTablerow(user, iter1.next(), tipperStateless, spieltagStateless));
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
		Iterator<TreppchenTablerow> iter1 = this.rows.iterator();
		while (iter1.hasNext())
		{
			sb.append(iter1.next().getHtmlRow());
		}
		return sb.toString();
	}

	public void sortName()
	{
		Collections.sort(this.rows, new SortTreppchenTableName());
	}

	public void sortSpieltag()
	{
		Collections.sort(this.rows, new SortTreppchenTableSpieltag());
	}

	public void sortPunkte()
	{
		Collections.sort(this.rows, new SortTreppchenTablePunkte());
	}

	public void sortGewinn()
	{
		Collections.sort(this.rows, new SortTreppchenTableGewinn());
	}
}
