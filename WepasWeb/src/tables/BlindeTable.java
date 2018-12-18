package tables;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sorts.SortBlindeTableBlindgrad;
import sorts.SortBlindeTableName;
import sorts.SortBlindeTableSpieltag;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpielException;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.SpieltagException;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TippException;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;

public class BlindeTable
{
	private static Log log = LogFactory.getLog(BlindeTable.class);

	Vector<BlindeTablerow> rows;
	BlindeTablehead1 head1;
	BlindeTablehead2 head2;

	public BlindeTable(EnTipper user, TippBeanRemote tippStateless, TipperBeanRemote tipperStateless, SpielBeanRemote spielStateless, SpieltagBeanRemote spieltagStateless, VereinBeanRemote vereinStateless)
	{
		super();
		this.rows = new Vector<BlindeTablerow>(50);
		this.head1 = new BlindeTablehead1();
		this.head2 = new BlindeTablehead2();
		Vector<EnTipp> tipps = null;
		try
		{
			EnTipp[] tipparray = tippStateless.getBlinde(); 
			tipps = new Vector<EnTipp>(tipparray.length);
			for (int i = 0; i < tipparray.length; i++)
			{
				tipps.add(tipparray[i]);
			}
			Iterator<EnTipp> iter1 = tipps.iterator();
			while (iter1.hasNext())
			{
				EnTipp tipp = iter1.next();
				EnTipper tipper = tipperStateless.getTipper(tipp.getIdTipper());
				EnSpiel spiel = spielStateless.getSpiel(tipp.getIdSpiel());
				EnSpieltag spieltag = spieltagStateless.getSpieltag(spiel.getFkSpielSpieltag());
				this.rows.add(new BlindeTablerow(user, tipper, tipp, spiel, spieltag, vereinStateless));
			}
			this.sortName();
			this.sortSpieltag();
		}
		catch (TippException e)
		{
			log.error("TippException Zeile 51 " + e.getMessage());
		} 
		catch (TipperException e)
		{
			log.error("TipperException Zeile nn " + e.getMessage());
		}
		catch (SpielException e)
		{
			log.error("SpielException Zeile nn " + e.getMessage());
		}
		catch (SpieltagException e)
		{
			log.error("SpieltagException Zeile nn " + e.getMessage());
		}
	}
	
	public String getHtmlRow()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append(this.head1.getHtmlRow());
		sb.append(this.head2.getHtmlRow());
		Iterator<BlindeTablerow> iter1 = this.rows.iterator();
		while (iter1.hasNext())
		{
			sb.append(iter1.next().getHtmlRow());
		}
		return sb.toString();
	}

	public void sortName()
	{
		Collections.sort(this.rows, new SortBlindeTableName());
	}

	public void sortSpieltag()
	{
		Collections.sort(this.rows, new SortBlindeTableSpieltag());
	}

	public void sortBlinde()
	{
		Collections.sort(this.rows, new SortBlindeTableBlindgrad());
	}
}
