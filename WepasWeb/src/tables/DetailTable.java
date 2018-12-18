package tables;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sorts.SortDetailTipps;
import sorts.SortTipperName;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TippException;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.VereinException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnVerein;

public class DetailTable
{
	private static Log log = LogFactory.getLog(DetailTable.class);

	String csstippoe = ApplConstants.CSSTIPPOE;
	String csstipp0e = ApplConstants.CSSTIPP0E;
	String csstipp1e = ApplConstants.CSSTIPP1E;
	String csstipp2e = ApplConstants.CSSTIPP2E;
	String csstipp4e = ApplConstants.CSSTIPP4E;

	DetailTablehead1 head1;
	DetailTablehead2 head2;
	DetailTablehead3 head3;
	DetailTablehead4 head4;
	EnVerein heim = null;
	EnVerein gast = null;
	Vector<EnTipp> tipps = null;
	Vector<DetailTablerow> rowsTendenzen = null;
	Vector<DetailTablerow> rowsNamen = null;

	int sieg = 0;
	int siegd = 0;
	int siegg = 0;
	int siegk = 0;
	int unentschieden = 0;
	int niederlage = 0;
	int niederlagek = 0;
	int niederlageg = 0;
	int niederlaged = 0;
	int siegst = 0;
	int unentschiedenst = 0;
	int niederlagest = 0;

	public DetailTable(EnTipper user, EnSpiel spiel, TippBeanRemote tippStateless, TipperBeanRemote tipperStateless, VereinBeanRemote vereinStateless)
	{
		super();
		String cssSd = csstippoe;
		String cssSg = csstippoe;
		String cssSk = csstippoe;
		String cssU = csstippoe;
		String cssNk = csstippoe;
		String cssNg = csstippoe;
		String cssNd = csstippoe;

		try
		{
			this.heim = (EnVerein) vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
			this.gast = (EnVerein) vereinStateless.getVerein(spiel.getFkSpielVereinGast());
		}
		catch (VereinException e)
		{
			log.info("VereinException in Zeile 75 " + e.getMessage());
		}
		
		try
		{
			EnTipp[] tipparray = tippStateless.getTipps(spiel);
			tipps = new Vector<EnTipp>(tipparray.length);
			for(int i = 0; i < tipparray.length; i++)
			{
				tipps.add(tipparray[i]);
			}
			
		}
		catch (TippException e)
		{
			log.error("TippException in Zeile 90 " + e.getMessage());
		}
		Iterator<EnTipp> iter1 = tipps.iterator();
		
		Vector<EnTipp> aufSieg = new Vector<EnTipp>(20);
		Vector<EnTipp> aufUnentschieden = new Vector<EnTipp>(20);
		Vector<EnTipp> aufNiederlage = new Vector<EnTipp>(20);
		
		while(iter1.hasNext())
		{
			EnTipp tipp = iter1.next();
			if(tipp.getAtTippTorHeim() > tipp.getAtTippTorGast())
			{
				aufSieg.add(tipp);
				sieg++;
				if(tipp.getAtTippIsSupertipp())
				{
					siegst++;
				}
				if(tipp.getAtTippTorHeim() - tipp.getAtTippTorGast() == 1)
				{
					siegk++;
				}
				if(tipp.getAtTippTorHeim() - tipp.getAtTippTorGast() == 2)
				{
					siegg++;
				}
				if(tipp.getAtTippTorHeim() - tipp.getAtTippTorGast() > 2)
				{
					siegd++;
				}
			}
			if(tipp.getAtTippTorHeim() == tipp.getAtTippTorGast() && tipp.getAtTippTorHeim() != 9)
			{
				aufUnentschieden.add(tipp);
				unentschieden++;
				if(tipp.getAtTippIsSupertipp())
				{
					unentschiedenst++;
				}
			}
			if(tipp.getAtTippTorHeim() < tipp.getAtTippTorGast())
			{
				aufNiederlage.add(tipp);
				niederlage++;
				if(tipp.getAtTippIsSupertipp())
				{
					niederlagest++;
				}
				if(tipp.getAtTippTorHeim() - tipp.getAtTippTorGast() == -1)
				{
					niederlagek++;
				}
				if(tipp.getAtTippTorHeim() - tipp.getAtTippTorGast() == -2)
				{
					niederlageg++;
				}
				if(tipp.getAtTippTorHeim() - tipp.getAtTippTorGast() < -2)
				{
					niederlaged++;
				}
			}
		}
		if(spiel.getAtSpielIsPlayed())
		{
			if(spiel.getAtSpielToreHeim() > spiel.getAtSpielToreGast())
			{
				cssU = csstipp0e;
				cssNk = csstipp0e;
				cssNd = csstipp0e;
				cssNg = csstipp0e;
				if(spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast() == 1)
				{
					cssSk = csstipp4e;
					cssSd = csstipp1e;
					cssSg = csstipp1e;
				}
				if(spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast() == 2)
				{
					cssSk = csstipp1e;
					cssSd = csstipp1e;
					cssSg = csstipp4e;
				}
				if(spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast() > 2)
				{
					cssSk = csstipp1e;
					cssSd = csstipp4e;
					cssSg = csstipp1e;
				}
			}
			if(spiel.getAtSpielToreHeim() == spiel.getAtSpielToreGast())
			{
				cssU = csstipp2e;
				cssSk = csstipp0e;
				cssSd = csstipp0e;
				cssSg = csstipp0e;
				cssNk = csstipp0e;
				cssNd = csstipp0e;
				cssNg = csstipp0e;
			}
			if(spiel.getAtSpielToreHeim() < spiel.getAtSpielToreGast())
			{
				cssU = csstipp0e;
				cssSk = csstipp0e;
				cssSd = csstipp0e;
				cssSg = csstipp0e;
				if(spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast() == -1)
				{
					cssNk = csstipp4e;
					cssNd = csstipp1e;
					cssNg = csstipp1e;
				}
				if(spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast() == -2)
				{
					cssNk = csstipp1e;
					cssNd = csstipp1e;
					cssNg = csstipp4e;
				}
				if(spiel.getAtSpielToreHeim() - spiel.getAtSpielToreGast() < -2)
				{
					cssNk = csstipp1e;
					cssNd = csstipp4e;
					cssNg = csstipp1e;
				}
			}
		}
		
//--> Jetzt kommt die Tabelle sortiert nach Tendenzen	
		this.head1 = new DetailTablehead1(spiel, heim, gast);
		this.head3 = new DetailTablehead3(siegd, cssSd, siegg, cssSg, siegk, cssSk, unentschieden, cssU, niederlagek, cssNk, niederlageg, cssNg, niederlaged, cssNd);
		this.head2 = new DetailTablehead2(sieg, siegst, unentschieden, unentschiedenst, niederlage, niederlagest);
		this.head4 = new DetailTablehead4();

		Collections.sort(aufSieg, new SortDetailTipps());
		Collections.sort(aufUnentschieden, new SortDetailTipps());
		Collections.sort(aufNiederlage, new SortDetailTipps());
		Iterator<EnTipp> iterS = aufSieg.iterator();
		Iterator<EnTipp> iterU = aufUnentschieden.iterator();
		Iterator<EnTipp> iterN = aufNiederlage.iterator();
		this.rowsTendenzen = new Vector<DetailTablerow>();
		while(iterS.hasNext() || iterU.hasNext() || iterN.hasNext())
		{
			EnTipp tippS = null;
			EnTipp tippU = null;
			EnTipp tippN = null;
			if(iterS.hasNext())
			{
				tippS = iterS.next();
			}
			if(iterU.hasNext())
			{
				tippU = iterU.next();
			}
			if(iterN.hasNext())
			{
				tippN = iterN.next();
			}
			this.rowsTendenzen.add(new DetailTablerow(user, tipperStateless, tippS, tippU, tippN));
		}
		
//--> Jetzt kommt die Tabelle sortiert nach Namen	
		
		Vector<EnTipper> tippers = null;
		try
		{
			EnTipper[] tipperarray = tipperStateless.getTipper();
			tippers = new Vector<EnTipper>();
			for(int i = 0; i < tipperarray.length;  i++)
			{
				tippers.add(tipperarray[i]);
			}
		}
		catch(TipperException e)
		{
			log.error("TipperException in Zeile 264 " + e.getMessage());
		}
		Collections.sort(tippers, new SortTipperName());
		Vector<EnTipper>tippersA = new Vector<EnTipper>();
		Vector<EnTipper>tippersB = new Vector<EnTipper>();
		Vector<EnTipper>tippersC = new Vector<EnTipper>();
		
		int spaltenanzahl = tippers.size() / 3;
		Iterator<EnTipper> iter2 = tippers.iterator();
		
		int i = 0;
		while(iter2.hasNext())
		{
			EnTipper tipper = iter2.next();
			if(i < spaltenanzahl)
			{
				tippersA.add(tipper);
			}
			else if(i < spaltenanzahl * 2)
			{
				tippersB.add(tipper);
			}
			else
			{
				tippersC.add(tipper);
			}
			i++;
		}
		Iterator<EnTipper> iterA = tippersA.iterator();
		Iterator<EnTipper> iterB = tippersB.iterator();
		Iterator<EnTipper> iterC = tippersC.iterator();
		this.rowsNamen = new Vector<DetailTablerow>();

		while(iterA.hasNext() || iterB.hasNext() || iterC.hasNext())
		{
			EnTipp tippA = null;
			EnTipp tippB = null;
			EnTipp tippC = null;
			if(iterA.hasNext())
			{
				try
				{
					tippA = tippStateless.getTipps(iterA.next(), spiel);
				}
				catch (TippException e)
				{
					log.error("TippException in Zeile 310 " + e.getMessage());
				}
			}
			if(iterB.hasNext())
			{
				try
				{
					tippB = tippStateless.getTipps(iterB.next(), spiel);
				}
				catch (TippException e)
				{
					log.error("TippException in Zeile 321 " + e.getMessage());
				}
			}
			if(iterC.hasNext())
			{
				try
				{
					tippC = tippStateless.getTipps(iterC.next(), spiel);
				}
				catch (TippException e)
				{
					log.error("TippException in Zeile 332 " + e.getMessage());
				}
			}
			this.rowsNamen.add(new DetailTablerow(user, tipperStateless, tippA, tippB, tippC));
		}
	}
	
	public String getHtmlRow1()
	{
		StringBuffer sb = new StringBuffer("");
		sb.append(this.head1.getHtmlRow());
		sb.append(this.head2.getHtmlRow());
		sb.append(this.head3.getHtmlRow());
		sb.append(this.head4.getHtmlRow());

		Iterator<DetailTablerow> iter1 = this.rowsTendenzen.iterator();
		while(iter1.hasNext())
		{
			sb.append(iter1.next().getHtmlRow());
		}
		return sb.toString();
	}
	
	public String getHtmlRow2()
	{
		StringBuffer sb = new StringBuffer("");
		Iterator<DetailTablerow> iter1 = this.rowsNamen.iterator();
		while(iter1.hasNext())
		{
			sb.append(iter1.next().getHtmlRow());
		}
		sb.append(this.head4.getHtmlRow());
		return sb.toString();
	}
}
