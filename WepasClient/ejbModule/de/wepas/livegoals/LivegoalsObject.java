package de.wepas.livegoals;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Vector;

import de.wepas.connector.SpielBeanLocal;
import de.wepas.connector.SpielException;
import de.wepas.connector.SpieltagBeanLocal;
import de.wepas.connector.SpieltagException;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.util.LookupLocalService;

/*
 * Dieses LivegolsObject wird Freitag abends angelegt und besteht als ein Objekt
 * das ganze Wochenende. Hier werden aktuelle Spielstände gespeichert. Bei Veränderung
 * werdend die entsprechenden Spiele geklont und historisiert. Angelegt mit der Create-
 * Methode und gespeichert wird dieses Ojbekt in der LivegoalsBean (Singleton) .
 */

public class LivegoalsObject implements Serializable
{
	private static final long serialVersionUID = 1144904040281163805L;
	private static int lfdnr = 0;
	private EnSpieltag spieltag = null;
	private Vector<Livespiel> livespiele = null;
	private Vector<Livespiel> livehistorie = null;
	private GregorianCalendar timestamp = null;
	private String htmltabelleaktuell = null;
	
	public LivegoalsObject()
	{
		lfdnr++;
		this.timestamp = new GregorianCalendar();
		this.livehistorie = new Vector<Livespiel>(100);
		
		SpieltagBeanLocal stbl = LookupLocalService.lookupSpieltagBeanLocal();
		
		try
		{
			this.spieltag = stbl.getCurrentSpieltag();
		}
		catch (SpieltagException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.spieltag.getAtSpieltagIsFinished())
		{
			try
			{
				this.spieltag = stbl.getNextSpieltag();
			}
			catch (SpieltagException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SpielBeanLocal sbl = LookupLocalService.lookupSpielBeanLocal();
		this.livespiele = new Vector<Livespiel>(this.spieltag.getAtSpieltagPaarungen());
		try
		{
			EnSpiel[] spielearray = sbl.getSpiele(this.spieltag);
			for (int i = 0; i < spielearray.length; i++)
			{
				Livespiel lg = new Livespiel(spielearray[i]);
				this.livespiele.addElement(lg);
			}
		}
		catch (SpielException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EnSpieltag getSpieltag()
	{
		return spieltag;
	}
	
	public Vector<Livespiel> getLivespiele()
	{
		return livespiele;
	}
	
	public Vector<Livespiel> getLivehistorie()
	{
		return livehistorie;
	}
	
	public String getHtmltabelleaktuell()
	{
		return htmltabelleaktuell;
	}

	public void setHtmltabelleaktuell(String htmltabelleaktuell)
	{
		this.htmltabelleaktuell = htmltabelleaktuell;
	}

	public void addLivehistorie(Livespiel lg)
	{
		lg.setHtmltabelle(this.htmltabelleaktuell);
		this.livehistorie.add(lg);
	}

	@Override
	public String toString()
	{
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
		return lfdnr + " LivegoalsObject createt at " + dateFormat.format(this.timestamp.getTime())
				+ " um " + timeFormat.format(this.timestamp.getTime()) + " Uhr / " + super.toString();
	}
}
