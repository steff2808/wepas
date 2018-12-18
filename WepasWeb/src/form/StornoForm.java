package form;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.LookupRemoteService;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpielException;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.SpieltagException;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TippException;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.VereinException;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.connector.WetteException;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.jpa.EnVerein;
import de.wepas.jpa.EnWette;

public class StornoForm
{
	private static Log log = LogFactory.getLog(StornoForm.class);
	
	private boolean ButtonNavigation;
	private boolean ButtonAusfuehren;
	
	private char statusflag;
	private String message;
	private TipperBeanRemote tipperStateless;
	private SpielBeanRemote spielStateless;
	private TippBeanRemote tippStateless;
	private WetteBeanRemote wetteStateless;
	private SpieltagBeanRemote spieltagStateless;
	private VereinBeanRemote vereinStateless;
	
	Vector<String> messages = new Vector<String>(200);
	
	public StornoForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.message = "Konstruktor";
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonAusfuehren = req.getParameter("ausfuehren") != null;
		this.getResources();
	}
	
	public void action(HttpServletRequest req)
	{
		System.out.println("-->ES WIRD STORNIERT !");
		EnSpieltag stornospieltag = (EnSpieltag) req.getSession().getAttribute(ApplConstants.ANZEIGESPIELTAG);
		
		Vector<EnWette> zuloeschendewetten = null;
		Vector<EnTipper> zuaenderndetipper = new Vector<EnTipper>();
		Vector<EnTipp> zuaenderndetipps = new Vector<EnTipp>();
		Vector<EnSpiel> zuaenderndespiele = new Vector<EnSpiel>();
		Vector<EnVerein>zuaenderndevereine = new Vector<EnVerein>();
		
		try
		{
			EnWette[] wettearray = wetteStateless.getWetten(stornospieltag);
			zuloeschendewetten = new Vector<EnWette>(wettearray.length);
			for (int i = 0; i < wettearray.length; i++)
			{
				zuloeschendewetten.add(wettearray[i]);
			}
		}
		catch (WetteException e)
		{
			log.error("WetteException in Zeile 77 " + e.getMessage());
		}
		
		Iterator<EnWette> iter1 = zuloeschendewetten.iterator();
		while (iter1.hasNext())
		{
			EnTipper tipper = null;
			EnWette wette = iter1.next();
			try
			{
				tipper = tipperStateless.getTipper(wette.getIdTipper());
			}
			catch (TipperException e)
			{
				log.error("TipperException in Zeile 91 " + e.getMessage());
			}
			if (wette.getAtWetteIsGewonnen())
			{
				tipper.setAtTipperKonto(tipper.getAtTipperKonto().subtract(wette.getAtWetteGewinn()));
				tipper.setAtTipperSiege(tipper.getAtTipperSiege() - 1);
			}
			tipper.setAtTipperKonto(tipper.getAtTipperKonto().add(stornospieltag.getAtSpieltagKosten()));
			tipper.setAtTipperPunkte(tipper.getAtTipperPunkte() - wette.getAtWettePunkte());
			zuaenderndetipper.add(tipper);
			
			Vector<EnTipp> tipps = null;
			try
			{
				EnTipp[] tipparray = tippStateless.getTipps(tipper, stornospieltag);
				tipps = new Vector<EnTipp>(tipparray.length);
				for (int i = 0; i < tipparray.length; i++)
				{
					tipps.add(tipparray[i]);
				}
			}
			catch (TippException e)
			{
				log.error("TippException in Zeile 177 " + e.getMessage());
			}
			Iterator<EnTipp> iter2 = tipps.iterator();
			while (iter2.hasNext())
			{
				EnTipp tipp = iter2.next();
				tipp.setAtTippPunkte(0);
				tipp.setAtTippIsValid(false);
				tipp.setAtTippIsTendenz(false);
				tipp.setAtTippIsDifferenz(false);
				tipp.setAtTippIsExakt(false);
				zuaenderndetipps.add(tipp);
			}
		}
		EnVerein heim = null;
		EnVerein gast = null;
		Vector<EnSpiel> spiele = null;
		try
		{
			EnSpiel[] spielarray = spielStateless.getSpiele(stornospieltag);
			spiele = new Vector<EnSpiel>(spielarray.length);
			for (int i = 0; i < spielarray.length; i++)
			{
				spiele.add(spielarray[i]);
			}
		}
		catch (SpielException e)
		{
			log.error("SpielException in Zeile 142 " + e.getMessage());
		}
		Iterator<EnSpiel> iter3 = spiele.iterator();
		while (iter3.hasNext())
		{
			EnSpiel spiel = iter3.next();
			if (spiel.getAtSpielIsPlayed())
			{
				spiel.setAtSpielIsPlayed(false);
				spiel.setAtSpielToreHeim(0);
				spiel.setAtSpielToreGast(0);
				try
				{
					heim = vereinStateless.getVerein(spiel.getFkSpielVereinHeim());
				}
				catch (VereinException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try
				{
					gast = vereinStateless.getVerein(spiel.getFkSpielVereinGast());
				}
				catch (VereinException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(spiel.getAtSpielToreHeim() > spiel.getAtSpielToreGast())
				{
					heim.setAtVereinPunkte(heim.getAtVereinPunkte() - 3);
					heim.setAtVereinSi(heim.getAtVereinSi() - 1);
					gast.setAtVereinNi(gast.getAtVereinNi() - 1);
					heim.setAtVereinHPunkte(heim.getAtVereinHPunkte() - 3);
					heim.setAtVereinHSi(heim.getAtVereinHSi() - 1);
					gast.setAtVereinANi(gast.getAtVereinANi() - 1);
				}
				if(spiel.getAtSpielToreHeim() == spiel.getAtSpielToreGast())
				{
					heim.setAtVereinPunkte(heim.getAtVereinPunkte() - 1);
					gast.setAtVereinPunkte(gast.getAtVereinPunkte() - 1);
					heim.setAtVereinUn(heim.getAtVereinUn() - 1);
					gast.setAtVereinUn(gast.getAtVereinUn() - 1);
					heim.setAtVereinHPunkte(heim.getAtVereinHPunkte() - 1);
					gast.setAtVereinAPunkte(gast.getAtVereinAPunkte() - 1);
					heim.setAtVereinHUn(heim.getAtVereinHUn() - 1);
					gast.setAtVereinAUn(gast.getAtVereinAUn() - 1);
				}
				if(spiel.getAtSpielToreHeim() < spiel.getAtSpielToreGast())
				{
					gast.setAtVereinPunkte(gast.getAtVereinPunkte() - 3);
					heim.setAtVereinNi(heim.getAtVereinNi() - 1);
					gast.setAtVereinSi(gast.getAtVereinSi() - 1);
					gast.setAtVereinAPunkte(gast.getAtVereinAPunkte() - 3);
					heim.setAtVereinHNi(heim.getAtVereinHNi() - 1);
					gast.setAtVereinASi(gast.getAtVereinASi() - 1);
				}
				heim.setAtVereinSp(heim.getAtVereinSp() - 1);
				gast.setAtVereinSp(gast.getAtVereinSp() - 1);
				heim.setAtVereinTg(heim.getAtVereinTg() - spiel.getAtSpielToreHeim());
				heim.setAtVereinTk(heim.getAtVereinTk() - spiel.getAtSpielToreGast());
				gast.setAtVereinTg(gast.getAtVereinTg() - spiel.getAtSpielToreGast());
				gast.setAtVereinTk(gast.getAtVereinTk() - spiel.getAtSpielToreHeim());
				heim.setAtVereinHSp(heim.getAtVereinHSp() - 1);
				gast.setAtVereinASp(gast.getAtVereinASp() - 1);
				heim.setAtVereinHTg(heim.getAtVereinHTg() - spiel.getAtSpielToreHeim());
				heim.setAtVereinHTk(heim.getAtVereinHTk() - spiel.getAtSpielToreGast());
				gast.setAtVereinATg(gast.getAtVereinATg() - spiel.getAtSpielToreGast());
				gast.setAtVereinATk(gast.getAtVereinATk() - spiel.getAtSpielToreHeim());
				
				zuaenderndevereine.add(heim);
				zuaenderndevereine.add(gast);
				
				zuaenderndespiele.add(spiel);
			}
		}
		stornospieltag.setAtSpieltagIsStarted(false);
		stornospieltag.setAtSpieltagIsFinished(false);
		stornospieltag.setAtSpieltagEinsatz(new BigDecimal(0.0));
		
		// -->Jetzt Beginn Transaktion wäre cool
		try
		{
			spieltagStateless.changeSpieltag(stornospieltag);
		}
		catch (SpieltagException e)
		{
			log.error("SpieltagException in Zeile 229 " + e.getMessage());
		}
		try
		{
			Iterator<EnTipper> iter4 = zuaenderndetipper.iterator();
			while (iter4.hasNext())
			{
				tipperStateless.changeTipper(iter4.next());
			}
		}
		catch (TipperException e)
		{
			log.error("TipperException in Zeile 243 " + e.getMessage());
		}
		try
		{
			Iterator<EnTipp> iter5 = zuaenderndetipps.iterator();
			while (iter5.hasNext())
			{
				tippStateless.changeTipp(iter5.next());
			}
		}
		catch (TippException e)
		{
			log.error("TippException in Zeile 255 " + e.getMessage());
		}
		try
		{
			Iterator<EnSpiel> iter6 = zuaenderndespiele.iterator();
			while (iter6.hasNext())
			{
				spielStateless.changeSpiel(iter6.next());
			}
		}
		catch (SpielException e)
		{
			log.error("SpielException in Zeile 267 " + e.getMessage());
		}
		try
		{
			Iterator<EnWette> iter7 = zuloeschendewetten.iterator();
			while (iter7.hasNext())
			{
				wetteStateless.deleteWette(iter7.next().getIdWette());
			}
		}
		catch (WetteException e)
		{
			log.error("WetteException in Zeile 279 " + e.getMessage());
		}
		try
		{
			Iterator<EnVerein> iter8 = zuaenderndevereine.iterator();
			while (iter8.hasNext())
			{
				vereinStateless.changeVerein(iter8.next());
			}
		}
		catch (VereinException e)
		{
			log.error("VereinException in Zeile 296 " + e.getMessage());
		}
	}
	
	public void todos(HttpServletRequest req)
	{
		EnSpieltag stornospieltag = (EnSpieltag) req.getSession().getAttribute(ApplConstants.ANZEIGESPIELTAG);
		this.message = "ToDo's für die Stornierung des " + stornospieltag.getAtSpieltagText();
		
		this.messages.add("Storno des " + stornospieltag.getAtSpieltagText());
		Vector<EnWette> wetten = null;
		EnTipper tipper = null;
		try
		{
			EnWette[] wettearray = wetteStateless.getWetten(stornospieltag);
			wetten = new Vector<EnWette>(wettearray.length);
			for (int i = 0; i < wettearray.length; i++)
			{
				wetten.add(wettearray[i]);
			}
		}
		catch (WetteException e)
		{
			log.error("WetteException in Zeile 302 " + e.getMessage());
		}
		Iterator<EnWette> iter1 = wetten.iterator();
		while (iter1.hasNext())
		{
			EnWette wette = iter1.next();
			try
			{
				tipper = tipperStateless.getTipper(wette.getIdTipper());
			}
			catch (TipperException e)
			{
				log.error("WetteException in Zeile 314 " + e.getMessage());
			}
			messages.add(tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname() + " --> " + wette.getAtWettePunkte() + " zurückrechnen");
			if (wette.getAtWetteIsGewonnen())
			{
				messages.add("--> dazu den Gewinn von " + wette.getAtWetteGewinn() + " wieder einkassieren");
				messages.add("--> und die Anzahl der Siege zurückrechnen");
			}
			messages.add("--> Kosten/Spieltag wieder gutschreiben " + stornospieltag.getAtSpieltagKosten());
			messages.add("--> Neuer Kontostand ist " + tipper.getAtTipperKonto().add(stornospieltag.getAtSpieltagKosten().subtract(wette.getAtWetteGewinn())));
			messages.add("--> Spieltagpunkte von Gesamtpunkte abziehen (" + (tipper.getAtTipperPunkte() - wette.getAtWettePunkte()) + ") statt "
					+ tipper.getAtTipperPunkte());
			Vector<EnTipp> tipps = null;
			try
			{
				EnTipp[] tipparray = tippStateless.getTipps(tipper, stornospieltag);
				tipps = new Vector<EnTipp>(tipparray.length);
				for (int i = 0; i < tipparray.length; i++)
				{
					tipps.add(tipparray[i]);
				}
			}
			catch (TippException e)
			{
				log.error("TippException in Zeile 337 " + e.getMessage());
			}
			messages.add(tipps.size() + " Tipps von " + tipper.getAtTipperName() + " zurücksetzen (Punkte, isValid, isExakt, isDiffernz, isTendenz)");
		}
		
		Vector<EnSpiel> spiele = null;
		try
		{
			EnSpiel[] spielarray = spielStateless.getSpiele(stornospieltag);
			spiele = new Vector<EnSpiel>(spielarray.length);
			for (int i = 0; i < spielarray.length; i++)
			{
				spiele.add(spielarray[i]);
			}
		}
		catch (SpielException e)
		{
			log.error("SpielException in Zeile 354 " + e.getMessage());
		}
		
		Iterator<EnSpiel> iter3 = spiele.iterator();
		while (iter3.hasNext())
		{
			EnSpiel spiel = iter3.next();
			if (spiel.getAtSpielIsPlayed())
			{
				messages.add("--> Spiel " + spiel.getIdSpiel() + " " + spiel.getAtSpielToreHeim() + ":" + spiel.getAtSpielToreGast()
						+ " Ergebnis und Gültigkeit zurücksetzen");
			}
		}
		messages.add("-->Vom Spieltag " + stornospieltag.getAtSpieltagText() + " hasStarted und hasFinished auf false setzen");
		messages.add("Alle EnWette - Entitäten des Spieltages löschen");
	}
	
	private void getResources()
	{
		this.spielStateless = LookupRemoteService.lookupSpielBeanRemote();
		this.spieltagStateless = LookupRemoteService.lookupSpieltagBeanRemote();
		this.tippStateless = LookupRemoteService.lookupTippBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
		this.wetteStateless = LookupRemoteService.lookupWetteBeanRemote();
		this.vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
	}
	
	public char getStatusflag()
	{
		return statusflag;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public Vector<String> getMessages()
	{
		return messages;
	}
	
	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}
	
	public boolean isButtonAusfuehren()
	{
		return ButtonAusfuehren;
	}
}
