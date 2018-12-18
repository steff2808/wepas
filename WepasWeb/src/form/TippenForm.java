package form;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tables.TippenTable;
import util.LookupRemoteService;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpielException;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.SpieltagException;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TippException;
import de.wepas.connector.TippabgabeStatefulBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;

public class TippenForm
{
	private static Log log = LogFactory.getLog(TippenForm.class);
	
	private TippenTable tippentable = null;
	
	private char statusflag;
	private String message = "";
	private Vector<EnTipp> tipps;
	
	private boolean ButtonNavigation;
	private boolean ButtonSpeichern;
	
	private TipperBeanRemote tipperStateless = null;
	private SpielBeanRemote spielStateless = null;
	private TippBeanRemote tippStateless = null;
	private SpieltagBeanRemote spieltagStateless = null;
	
	// -->Konstruktor
	public TippenForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonSpeichern = req.getParameter("speichern") != null;
		log.debug("ButtonNavi=" + this.isButtonNavigation() + " | ButtonSpeichern=" + this.isButtonSpeichern());
		
		this.getResources();
		
		// -->Was wurde geklickt?
		if (this.isButtonNavigation())
		{
			buttonNavigation(req);
			return;
		}
		
		if (this.ButtonSpeichern && req.getSession().getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN)) != null)
		{
			buttonSpeichern(req);
			return;
		}
		
		if (req.getSession().getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN)) == null)
		{
			ohneButton(req);
			return;
		}
		req.getSession().removeAttribute(ApplConstants.TIPPABGABE_FREE);
	}
	
	private void ohneButton(HttpServletRequest req)
	{
		log.debug("---> Nix geklickt -> ANZEIGE TIPPFORMULAR FÜR PFLICHTSPIELE LEER");
		Context ctx = null;
		Object ref = null;
		try
		{
			ctx = new InitialContext();
			ref = ctx.lookup(ApplConstants.JNDITIPPABGABEBEAN);
		}
		catch (NamingException e)
		{
			e.printStackTrace();
			log.error("NamingException Zeile 183 " + e.getMessage());
			statusflag = 'F';
		}
		TippabgabeStatefulBeanRemote tippabgabeStateful = (TippabgabeStatefulBeanRemote) PortableRemoteObject.narrow(ref, TippabgabeStatefulBeanRemote.class);
		
		EnTipper tipper = null;
		try
		{
			tipper = tipperStateless.getTipper(Integer.parseInt(req.getParameter("tipperID")));
		}
		catch (Exception e)
		{
			log.error("Exception in Zeile 195 (" + e.getMessage() + ")");
			e.printStackTrace();
		}
		
		tippabgabeStateful.setTipper(tipper);
		
		Vector<EnSpieltag> spieltage = null;
		Vector<EnSpiel> spiele = null;
		
		try
		{
			EnSpieltag[] spieltagarray = spieltagStateless.getSpieltag();
			spieltage = new Vector<EnSpieltag>(spieltagarray.length);
			for (int i = 0; i < spieltagarray.length; i++)
			{
				spieltage.add(spieltagarray[i]);
			}
			
			// spieltage = sbr.getSpieltag();
		}
		catch (SpieltagException e)
		{
			log.error("SpieltagException Zeile 217 " + e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e)
		{
			log.error("Exception Zeile 222 " + e.getMessage());
			e.printStackTrace();
		}
		Iterator<EnSpieltag> iter1 = spieltage.iterator();
		EnSpieltag zutippenderspieltag = null;
		boolean getippt = false;
		
		while (iter1.hasNext())
		{
			EnSpieltag spieltag = iter1.next();
			if (!spieltag.getAtSpieltagIsFinished()
					&& (req.getSession().getAttribute(ApplConstants.TIPPABGABE_FREE) != null && spieltag.getAtSpieltagIsDuty() == false)
					|| (req.getSession().getAttribute(ApplConstants.TIPPABGABE_FREE) == null && spieltag.getAtSpieltagIsDuty() == true))
			{
				try
				{
					EnSpiel[] spielarray = spielStateless.getSpiele(spieltag);
					spiele = new Vector<EnSpiel>(spielarray.length);
					for (int i = 0; i < spielarray.length; i++)
					{
						spiele.add(spielarray[i]);
					}
					if (spiele.size() > 0)
					{
						EnTipp tipp = null; // Wenn er null bleibt ist das der
											// zu tippende Spieltag
						try
						{
							tipp = tippStateless.getTipps(tipper, spiele.firstElement());
						}
						catch (TippException e)
						{
							log.error("TippException Zeile 253 " + e.getMessage());
							e.printStackTrace();
						}
						catch (Exception e)
						{
							log.error("Exception Zeile 258 " + e.getMessage());
							e.printStackTrace();
						}
						if (tipp == null && !getippt)
						{
							zutippenderspieltag = spieltag;
							getippt = true;
						}
					}
				}
				catch (SpielException e)
				{
					log.error("SpielException Zeile 270 " + e.getMessage());
					e.printStackTrace();
				}
				catch (Exception e)
				{
					log.error("Exception Zeile 275 " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		tippabgabeStateful.setSpieltag(zutippenderspieltag);
		req.getSession().setAttribute(ApplConstants.TIPPABGABE_STATEFULBEAN, tippabgabeStateful);
		statusflag = '1';
		log.debug("STATUSFLAG 1 (1. Aufruf mit leerem Tippformular)");
		this.tippentable = new TippenTable(tippabgabeStateful, req);
		req.getSession().setAttribute(ApplConstants.TIPPENTABLE, this.tippentable);
		return;
	}
	
	private void buttonSpeichern(HttpServletRequest req)
	{
		log.debug("Button Speichern und STFSB vorhanden");
		Timestamp jetzt = new Timestamp(new GregorianCalendar().getTimeInMillis());
		log.debug("---> SFSB VORHANDEN ! Request nach den Tipps durchsuchen, Plausibilisieren und wenn OK speichern");
		// spiel.getAtSpielAnpfiff().compareTo(jetzt) < 0
		statusflag = 'K';
		log.debug("STATUSFLAG K (Beginn Kontrolle)");
		tipps = new Vector<EnTipp>();
		TippabgabeStatefulBeanRemote tippabgabeStateful = (TippabgabeStatefulBeanRemote) req.getSession().getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN));
		
		EnSpiel[] spielarray = tippabgabeStateful.getSpiele();
		Vector<EnSpiel> spiele = new Vector<EnSpiel>(spielarray.length);
		for (int i = 0; i < spielarray.length; i++)
		{
			spiele.add(spielarray[i]);
		}
		Iterator<EnSpiel> iter1 = spiele.iterator();
		while (iter1.hasNext())
		{
			EnTipper tipper = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
			EnSpiel spiel = iter1.next();
			
			EnTipp tipp = new EnTipp();
			tipp.setNumberFormat(true);
			tipp.setIdTipper(tipper.getIdTipper());
			tipp.setIdSpiel(spiel.getIdSpiel());
			try
			{
				tipp.setAtTippTorHeim(Integer.parseInt(req.getParameter(spiel.getIdSpiel() + ApplConstants.TXTTOREHEIM)));
				tipp.setAtTippTorGast(Integer.parseInt(req.getParameter(spiel.getIdSpiel() + ApplConstants.TXTTOREGAST)));
			}
			catch (NumberFormatException e)
			{
				tipp.setNumberFormat(false);
				message = "Fehler: " + "Bitte Tipps überprüfen - es sind nicht alle numerisch!";
				log.debug("STATUSFLAG T (Tipps sind nicht numerisch)");
				statusflag = 'T';
			}
//			if (tippabgabeStateful.getSpieltag().getIdSpieltag() > 3 && tipp.getAtTippTorHeim() == tipp.getAtTippTorGast())
//			{
//				message = "Fehler: " + "Bitte Tipps überprüfen - Finalspiele können nicht Unentschieden sein!";
//				statusflag = 'T';
//log.info("STATUSFLAG T (Tipps sind Fehlerhaft)");		
//			}
			tipp.setAtTippIsSupertipp(req.getParameter(spiel.getIdSpiel() + "CB_SUPER") != null);
			if (spiel.getAtSpielAnpfiff().compareTo(jetzt) < 0)
			{
				log.info("UNGUELTIG! - Tipp (" + tipp.getAtTippTorHeim() + ":" + tipp.getAtTippTorGast() + ") für SpielID " + spiel.getIdSpiel() + " von "
						+ tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
				tipp.setAtTippTorHeim(9);
				tipp.setAtTippTorGast(9);
			}
			tipps.add(tipp);
		}
		if (statusflag == 'K')
		{
			int st = 0;
			Iterator<EnTipp> iter2 = tipps.iterator();
			{
				while (iter2.hasNext())
				{
					if (iter2.next().getAtTippIsSupertipp())
					{
						st++;
					}
				}
			}
			
			if (st != tippabgabeStateful.getSpieltag().getAtSpieltagSupertipps())
			{
				message = "Fehler: " + st + " Supertipps statt der " + tippabgabeStateful.getSpieltag().getAtSpieltagSupertipps()
						+ " für diesen Spieltag gesetzt.";
				log.debug("STATUSFLAG S (Supertipps sind fehlerhaft)");
				statusflag = 'S';
			}
		}
		if (tippabgabeStateful.getSpieltag().getAtSpieltagPaarungen() == tipps.size() && statusflag == 'K')
		{
			log.info("TippabgabeStatefulBean --->Tipps von " + tippabgabeStateful.getTipper().getAtTipperVorname() + " "
					+ tippabgabeStateful.getTipper().getAtTipperName() + " und " + tippabgabeStateful.getSpieltag().getAtSpieltagText()
					+ " wurden gespeichert.");
			this.actionOK(req);
		}
		else
		{
//-->Tabelle nochmal neu aufbauen mit den erfaßten Tipps
			this.tippentable = new TippenTable(tippabgabeStateful, req);
			req.getSession().setAttribute(ApplConstants.TIPPENTABLE, this.tippentable);
		}
		return;
	}
	
	private void buttonNavigation(HttpServletRequest req)
	{
		log.info("--->ButtonNavi -->Unabhängig vom Zustand wurde 'zurück' geklickt - also alles abbrechen und ggf. SFSB freigeben.");
		if (req.getSession().getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN)) != null)
		{
			log.debug("STATUSFLAG N (SFSB löschen und freigeben");
		}
		statusflag = 'N';
		return;
	}
	
	private void actionOK(HttpServletRequest req)
	{
		log.info("--->actionOK");

		TippabgabeStatefulBeanRemote tippabgabeStateful = (TippabgabeStatefulBeanRemote) req.getSession().getAttribute(ApplConstants.TIPPABGABE_STATEFULBEAN);
		try
		{
			Iterator<EnTipp> iter1 = this.tipps.iterator();
			while (iter1.hasNext())
			{
				tippabgabeStateful.addTipp(iter1.next());
			}
		}
		catch (TippException e)
		{
			log.error("TippException Zeile 313 " + e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e)
		{
			log.error("Exception Zeile 318 " + e.getMessage());
			e.printStackTrace();
		}
		req.getSession().setAttribute(ApplConstants.ANZEIGESPIELTAG, tippabgabeStateful.getSpieltag());
		statusflag = '9';
		message = "Deine Tipps '<b>" + tippabgabeStateful.getSpieltag().getAtSpieltagText() + "</b>' wurden gespeichert.";
		tippabgabeStateful.remove();
		req.getSession().removeAttribute(ApplConstants.TIPPABGABE_STATEFULBEAN);
		req.getSession().removeAttribute(ApplConstants.TIPPABGABE_FREE);
	}
	
	private void getResources()
	{
		this.spielStateless = LookupRemoteService.lookupSpielBeanRemote();
		this.spieltagStateless = LookupRemoteService.lookupSpieltagBeanRemote();
		this.tippStateless = LookupRemoteService.lookupTippBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
	}
	
	// -->Getter und Setter-Methoden
	public char getStatusflag()
	{
		return statusflag;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}
	
	public boolean isButtonSpeichern()
	{
		return ButtonSpeichern;
	}
}
