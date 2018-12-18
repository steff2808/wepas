package form;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tables.MainTable;
import tables.MainTablerow;
import util.LookupRemoteService;
import de.wepas.connector.EinstellungBeanRemote;
import de.wepas.connector.EinstellungException;
import de.wepas.connector.ForumBeanRemote;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.SpieltagException;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TippException;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.TipperException;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnEinstellung;
import de.wepas.jpa.EnSpiel;
import de.wepas.jpa.EnSpieltag;
import de.wepas.jpa.EnTipp;
import de.wepas.jpa.EnTipper;
import de.wepas.livegoals.LivegoalsBeanRemote;

public class MainForm
{
	private static Log log = LogFactory.getLog(MainForm.class);
	
	private SpielBeanRemote spielStateless;
	private TipperBeanRemote tipperStateless;
	private VereinBeanRemote vereinStateless;
	private SpieltagBeanRemote spieltagStateless;
	private WetteBeanRemote wetteStateless;
	private TippBeanRemote tippStateless;
	private EinstellungBeanRemote einstellungStateless;
	private ForumBeanRemote forumStateless;
	private LivegoalsBeanRemote lifegoalsSingleton;
	
//-->Mögliche Buttons in der Navigation.jsp-Seite	
	private boolean ButtonLivegoals;
	private boolean ButtonLogout;
	private boolean ButtonTippenduty;
	private boolean ButtonTippenfree;
	private boolean ButtonTabelle;
	private boolean ButtonStatistik;
	private boolean ButtonLaenderranking;
	private boolean ButtonForum;
	private boolean ButtonAdmin;
	private boolean ButtonNext;
	private boolean ButtonPrev;
	private boolean ButtonSortName;
	private boolean ButtonSortPunkteGesamt;
	private boolean ButtonSortPunkteSpieltag;
	private boolean ButtonSortTipperKonto;
	private boolean ButtonStorno;
	private int InfoSpielId = 0;
	private int SortSpielId = 0;
	
	private MainTable maintable;
	private char statusflag;
	private String message;
	private EnSpieltag spieltagnext = null;
	private EnSpieltag spieltagcurr = null;
	
	private boolean spieltaganzeige = true;
	private boolean tunier = false;
	
	private EnTipper user = null;
	private HttpServletRequest req;
	
	public MainForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.req = req;
		this.user = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
		if(this.user == null)
		{
			req.getSession().setAttribute("TIMEOUT", "Timeout, bitte neu anmelden");
			log.info("ACHTUNG - Timeout - Login-Formular wird angezeigt!");
			this.ButtonLogout = true;
			return;
		}

//-->Ermitteln ob Navibutton geklickt wurde
		this.ButtonLogout = req.getParameter(ApplConstants.BUTTONLOGOUT) != null;
		this.ButtonTippenduty = req.getParameter(ApplConstants.BUTTONTIPPENDUTY) != null;
		this.ButtonTippenfree = req.getParameter(ApplConstants.BUTTONTIPPENFREE) != null;
		this.ButtonTabelle = req.getParameter(ApplConstants.BUTTONTABELLE) != null;
		this.ButtonStatistik = req.getParameter(ApplConstants.BUTTONSTATISTIK) != null;
		this.ButtonLaenderranking = req.getParameter(ApplConstants.BUTTONLAENDERRANKING) != null;
		this.ButtonForum = req.getParameter(ApplConstants.BUTTONFORUM) != null;
		this.ButtonAdmin = req.getParameter(ApplConstants.BUTTONADMIN) != null;
		this.ButtonStorno = req.getParameter(ApplConstants.BUTTONSTORNO) != null;

//-->Ermitteln ob Funktionsbutton geklickt wurde		
		this.ButtonLivegoals = req.getParameter(ApplConstants.BUTTONLIVEGOALS) != null;

//-->Ermitteln ob Sortbutton geklickt wurde		
		this.ButtonSortName = req.getParameter(ApplConstants.BUTTONSORTNAME) != null;
		this.ButtonSortPunkteGesamt = req.getParameter(ApplConstants.BUTTONSORTPUNKTEGESAMT) != null;
		this.ButtonSortPunkteSpieltag = req.getParameter(ApplConstants.BUTTONSORTPUNKTESPIELTAG) != null;
		this.ButtonSortTipperKonto = req.getParameter(ApplConstants.BUTTONSORTTIPPERKONTO) != null;

//-->Ermitteln ob weitere Spieltage angezeigt werden sollen
		this.ButtonNext = req.getParameter(ApplConstants.BUTTONNEXT) != null;
		this.ButtonPrev = req.getParameter(ApplConstants.BUTTONPREV) != null;
		
//-->Ermitteln ob ein Info-Button für ein bestimmtes Spiel angeklickt wurde
		this.maintable = (MainTable) req.getSession().getAttribute(ApplConstants.NAVITABLE);
		if(this.maintable != null)
		{
			Iterator<EnSpiel> iter1 = this.maintable.getSpiele().iterator();
			while(iter1.hasNext())
			{
				EnSpiel spiel = iter1.next();
				if(req.getParameter("sort" + spiel.getIdSpiel()) != null)
				{
					this.SortSpielId = spiel.getIdSpiel();
					EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
					log.info("--> (SortSpiel) " + spiel.getFkSpielVereinHeim() + "-" + spiel.getFkSpielVereinGast() + " " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
				}
				if(req.getParameter("info" + spiel.getIdSpiel()) != null)
				{
					this.InfoSpielId = spiel.getIdSpiel();
					EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
					log.info("--> (InfoSpiel) " + spiel.getFkSpielVereinHeim() + "-" + spiel.getFkSpielVereinGast() + " " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
				}
			}
		}
		
//-->Jetzt geht's ans auswerten		
		if (this.ButtonLogout || this.ButtonTippenduty || this.ButtonTippenfree ||
			this.ButtonTabelle || this.ButtonStatistik || this.ButtonLaenderranking||
			this.ButtonForum || this.ButtonAdmin ||
			this.ButtonLivegoals || this.ButtonStorno || this.InfoSpielId > 0)
		{
//--> Unabhängig vom Zustand wurde ein NaviButton geklickt - also alles abbrechen und ggf. SFSB freigeben.
//--> Oder wurde für ein Spiel die Detailanzeige geklickt?	
			if (req.getSession().getAttribute((ApplConstants.TIPPABGABE_STATEFULBEAN)) != null)
			{
				statusflag = 'N';
				return;
			}
			return;
		}

//-->Jetzt geht es auf jeden fall in der Navi-Seite weiter.
		this.getResources();
		
//--> Ggf wurde ein Sortbutton für diese Seite geklickt?
		if (this.ButtonSortName)
		{
			SortName(req);
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (SortName) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
			return;
		}
		
		if (this.ButtonSortPunkteGesamt)
		{
			SortPunkteGesamt(req);
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (SortPktG) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
			return;
		}
		
		if (this.ButtonSortPunkteSpieltag)
		{
			SortPunkteSpieltag(req);
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (SortPkt ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
			return;
		}
		
		if (this.ButtonSortTipperKonto)
		{
			SortTipperKonto(req);
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (SortKto ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
			return;
		}
		
		if (this.SortSpielId > 0)
		{
			SortSpiel(req, this.SortSpielId);
			return;
		}
		
//Jetzt wurde entweder ein Funktionsbutton auf dieser Seite geklickt oder gar kein Button, dann Defaultspieltag
		if (this.ButtonNext)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Next ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
			ErmittelnSpieltagNext(req);
		}
		else if (this.ButtonPrev)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Prev ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
			ErmittelnSpieltagPrev(req);
		}
		else if(req.getSession().getAttribute(ApplConstants.ANZEIGESPIELTAG) == null)
		{
//Kein Sessionattribut vorhanden, dann den aktuellen Spieltag ermitteln und in der Session speichern
			ErmittelnSpieltagDefault(req);	
		}
//-->Jetzt muss ein Session-Objekt da sein!
		CreateSpieltagTable(req);
		SortSpieltagTable(req);
	}

public int getInfoSpielId()
	{
		return InfoSpielId;
	}

//-->Es folgen Methoden um die Tabelle zu sortieren
	private void SortTipperKonto(HttpServletRequest req)
	{
		this.spieltaganzeige = false;
		this.maintable = (MainTable) req.getSession().getAttribute(ApplConstants.NAVITABLE);
		maintable.sortTipperKonto();
		Iterator<MainTablerow> iter6 = this.maintable.getRows().iterator();
		int i = 0;
		double gruppenwechsel = 99.99;
		while (iter6.hasNext())
		{
			i++;
			MainTablerow row = iter6.next();
			row.setPlatz(0);
			row.setPlatzgesamt(0);
			if (gruppenwechsel != row.getGeldgesamt().doubleValue())
			{
				gruppenwechsel = row.getGeldgesamt().doubleValue();
				row.setPlatzgesamt(i);
			}
		}
	}

	private void SortName(HttpServletRequest req)
	{
		this.spieltaganzeige = true;
		EnSpieltag anzeigespieltag = (EnSpieltag) req.getSession().getAttribute(ApplConstants.ANZEIGESPIELTAG);
		this.maintable = (MainTable) req.getSession().getAttribute(ApplConstants.NAVITABLE);
		maintable.sortTipperName();

		if (anzeigespieltag.getAtSpieltagIsStarted())
		{
			Iterator<MainTablerow> iter4 = this.maintable.getRows().iterator();
			while (iter4.hasNext())
			{
				iter4.next().setPlatz(0);
			}
		}
	}

	private void SortPunkteSpieltag(HttpServletRequest req)
	{
		this.spieltaganzeige = true;
		this.maintable = (MainTable) req.getSession().getAttribute(ApplConstants.NAVITABLE);

		maintable.sortTipperPunkteSpieltag();
		Iterator<MainTablerow> iter5 = this.maintable.getRows().iterator();
		int i = 0;
		int gruppenwechsel = 99;
		while (iter5.hasNext())
		{
			i++;
			MainTablerow row = iter5.next();
			row.setPlatz(0);
			row.setPlatzgesamt(0);
			if (gruppenwechsel != row.getPunktespieltag())
			{
				gruppenwechsel = row.getPunktespieltag();
				row.setPlatz(i);
			}
		}
	}

	private void SortPunkteGesamt(HttpServletRequest req)
	{
		this.spieltaganzeige = false;
		this.maintable = (MainTable) req.getSession().getAttribute(ApplConstants.NAVITABLE);
		maintable.sortTipperPunkteGesamt();

		Iterator<MainTablerow> iter6 = this.maintable.getRows().iterator();
		int i = 0;
		int gruppenwechsel = 99;
		while (iter6.hasNext())
		{
			i++;
			MainTablerow row = iter6.next();
			row.setPlatz(0);
			row.setPlatzgesamt(0);
			if (gruppenwechsel != row.getPunktegesamt())
			{
				gruppenwechsel = row.getPunktegesamt();
				row.setPlatzgesamt(i);
			}
		}
	}
	
	private void SortSpiel(HttpServletRequest req, int spielId)
	{
		this.spieltaganzeige = true;
		this.maintable = (MainTable) req.getSession().getAttribute(ApplConstants.NAVITABLE);
		Iterator<MainTablerow> iter7 = this.maintable.getRows().iterator();
		while (iter7.hasNext())
		{
			iter7.next().setPlatz(0);
		}
		maintable.sortSpiel(spielId);
	}
	
//-->Es folgen Methoden zum ermitteln des anzuzeigenden Spieltages	
	private void ErmittelnSpieltagPrev(HttpServletRequest req)
	{
		this.spieltaganzeige = true;
		EnSpieltag anzeigespieltag = (EnSpieltag) req.getSession().getAttribute(ApplConstants.ANZEIGESPIELTAG);
		try
		{
			EnSpieltag[] spieltagarray = spieltagStateless.getSpieltag();
			Vector<EnSpieltag> spieltage = new Vector<EnSpieltag>(spieltagarray.length);
			for(int i = 0; i < spieltagarray.length; i++)
			{
				spieltage.add(spieltagarray[i]);
			}
			Iterator<EnSpieltag> iter1 = spieltage.iterator();

//				Iterator<EnSpieltag> iter1 = sbr.getSpieltag().iterator();
			EnSpieltag spieltagprev = null;
			while (iter1.hasNext())
			{
				EnSpieltag spieltag = iter1.next();
				if (anzeigespieltag.getIdSpieltag() == spieltag.getIdSpieltag())
				{
					anzeigespieltag = spieltagprev;
				}
				spieltagprev = spieltag;
			}
			req.getSession().setAttribute(ApplConstants.ANZEIGESPIELTAG, anzeigespieltag);
		}
		catch (SpieltagException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			//kann ja mal passieren
		}
		catch (Exception e)
		{
			log.error("Exception in Zeile 362 " + e.getMessage());
		}
	}
	
	private void ErmittelnSpieltagNext(HttpServletRequest req)
	{
		this.spieltaganzeige = true;
		EnSpieltag anzeigespieltag = (EnSpieltag) req.getSession().getAttribute(ApplConstants.ANZEIGESPIELTAG);
		try
		{
			EnSpieltag[] spieltagarray = spieltagStateless.getSpieltag();
			Vector<EnSpieltag> spieltage = new Vector<EnSpieltag>(spieltagarray.length);
			for(int i = 0; i < spieltagarray.length; i++)
			{
				spieltage.add(spieltagarray[i]);
			}
			Iterator<EnSpieltag> iter1 = spieltage.iterator();
		
//				Iterator<EnSpieltag> iter1 = sbr.getSpieltag().iterator();
			boolean gefunden = false;
			boolean exit = false;
			while (iter1.hasNext())
			{
				EnSpieltag spieltag = iter1.next();
				if (gefunden && !exit)
				{
					anzeigespieltag = spieltag;
					exit = true;
				}
				if (anzeigespieltag.getIdSpieltag() == spieltag.getIdSpieltag())
				{
					gefunden = true;
				}
			}
			req.getSession().setAttribute(ApplConstants.ANZEIGESPIELTAG, anzeigespieltag);
		}
		catch (SpieltagException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void ErmittelnSpieltagDefault(HttpServletRequest req)
	{
		// ---> Kein Session-Objekt da - weiter mit ermitteln des aktuellen Spieltages
		this.spieltaganzeige = true;
		this.spieltagnext = null;
		this.spieltagcurr = null;
		try
		{
			spieltagnext = spieltagStateless.getNextSpieltag();
		}
		catch (SpieltagException e)
		{
			log.info("---> Tippsaison ist zuende");
			log.info("--->" + e.getMessage());
			e.printStackTrace();
		}
		try
		{
			spieltagcurr = spieltagStateless.getCurrentSpieltag();
			req.getSession().setAttribute(ApplConstants.ANZEIGESPIELTAG, spieltagcurr);
		}
		catch (SpieltagException e)
		{
			log.info("---> Tippsaison noch nicht angefangen");
			req.getSession().setAttribute(ApplConstants.ANZEIGESPIELTAG, spieltagnext);
		}
	}
	
//-->Aus dem Sessionobjekt wird jetzt die Spieltagtabelle generiert und sortiert
	private void CreateSpieltagTable(HttpServletRequest req)
	{
// ---> Weiter mit Anzeige des in der Session gespeicherten Spieltages
		EnSpieltag anzeigespieltag = (EnSpieltag) req.getSession().getAttribute(ApplConstants.ANZEIGESPIELTAG);
		this.maintable = new MainTable(user, anzeigespieltag, this.spielStateless, this.vereinStateless, 
				this.einstellungStateless, this.forumStateless, this.lifegoalsSingleton);
		Vector<MainTablerow> rows = maintable.getRows();
		Vector<EnTipper> tippers = new Vector<EnTipper>();
		try
		{
			EnTipper[] tipperarray = tipperStateless.getTipper();
			for (int i = 0; i < tipperarray.length; i++)
			{
				tippers.add(tipperarray[i]);
			}
		}
		catch (TipperException e)
		{
			log.error("TipperException in Zeile 453 "+ e.getMessage());
			e.printStackTrace();
		}
		
		this.tunier = true;
		EnEinstellung tuniermodus = null;
		try
		{
			tuniermodus = this.einstellungStateless.getEinstellung("tunier")[0];
			this.tunier = tuniermodus.getAtEinstellungWertBool() == 1;
		}
		catch (EinstellungException e)
		{
			log.error("EinstellungException in Zeile 465 " + e.getMessage());
		}
//		System.out.println("--->"  + this.tunier + tuniermodus.getAtEinstellungWertBool()+ tuniermodus.getAtEinstellungSchluessel());

		
		boolean owngetippt = false;
		Iterator<EnTipper> iter1 = tippers.iterator();
		while (iter1.hasNext())
		{
			EnTipper tipper = iter1.next();
			if(tipper.getIdTipper() == this.user.getIdTipper())
			{
				EnTipp[] tipparray = null;
				try
				{
					tipparray = tippStateless.getTipps(tipper, anzeigespieltag);
				}
				catch (TippException e)
				{
					log.error("TippException in Zeile 484 "+ e.getMessage());
				}
				owngetippt = (tipparray.length > 0);
			}
			MainTablerow row = new MainTablerow(this.tunier, user, anzeigespieltag, tipper, wetteStateless, tippStateless);
			row.setOwn(tipper.getIdTipper() == this.user.getIdTipper());
			rows.add(row);
		}
		if(owngetippt)
		{
			Iterator<MainTablerow> iter3 = rows.iterator();
			while(iter3.hasNext())
			{
				iter3.next().setOwnGetippt(true);
			}
		}
		maintable.setOwnGetippt(owngetippt);
		maintable.calculatePlatz();
		req.getSession().setAttribute(ApplConstants.NAVITABLE, maintable);
	}
	
	private void SortSpieltagTable(HttpServletRequest req)
	{
		EnSpieltag anzeigespieltag = (EnSpieltag) req.getSession().getAttribute(ApplConstants.ANZEIGESPIELTAG);
		if (anzeigespieltag.getAtSpieltagIsStarted() || anzeigespieltag.getAtSpieltagIsFinished())
		{
			this.maintable.sortTipperPunkteSpieltag();
// TODO			this.wepastable.sortTipperPunkteGesamt();
			Iterator<MainTablerow> iter3 = this.maintable.getRows().iterator();
			int i = 0;
			int gruppenwechsel = 99;
			while (iter3.hasNext())
			{
				i++;
				MainTablerow row = iter3.next();
				row.setPlatz(0);
				if (gruppenwechsel != row.getPunktespieltag())
				{
					gruppenwechsel = row.getPunktespieltag();
					row.setPlatz(i);
				}
			}
		}
		else
		{
			this.maintable.sortTipperName();
		}
	}

//--->Es folgen Getter/Settermethoden	
	public boolean isButtonLogout()
	{
		if(ButtonLogout)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			try
			{
				log.info("--> LOGOFF " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
			}
			catch(NullPointerException e)
			{
				//Das kann schonmal sein.
			}
		}
		return ButtonLogout;
	}
	
	public boolean isButtonTippenduty()
	{
		if(ButtonTippenduty)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Tippenduty) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonTippenduty;
	}
	
	public boolean isButtonTippenfree()
	{
		if(ButtonTippenfree)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			req.getSession().setAttribute((ApplConstants.TIPPABGABE_FREE), "FREE");
			log.info("--> (Tippenfree) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonTippenfree;
	}
	
	public boolean isButtonForum()
	{
		return ButtonForum;
	}
	
	public boolean isButtonAdmin()
	{
		return ButtonAdmin;
	}
	
	public boolean isButtonStorno()
	{
		return ButtonStorno;
	}
	
	public boolean isButtonTabelle()
	{
		if(ButtonTabelle)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Tabelle) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonTabelle;
	}
	
	public boolean isButtonStatistik()
	{
		if(ButtonStatistik)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonStatistik;
	}
	
	public boolean isButtonLivegoals()
	{
		if(ButtonLivegoals)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Livegoals) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonLivegoals;
	}
	
	public boolean isButtonLaenderranking()
	{
		if(ButtonLaenderranking)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Laender) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonLaenderranking;
	}
	
	public char getStatusflag()
	{
		return statusflag;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public EnSpieltag getSpieltagnext()
	{
		return spieltagnext;
	}
	
	public EnSpieltag getSpieltagcurr()
	{
		return spieltagcurr;
	}
	
	public EnTipper getUser()
	{
		return this.user;
	}
	
	public boolean isSpieltaganzeige()
	{
		return spieltaganzeige;
	}
	
	private void getResources()
	{
		this.spielStateless = LookupRemoteService.lookupSpielBeanRemote();
		this.spieltagStateless = LookupRemoteService.lookupSpieltagBeanRemote();
		this.tippStateless = LookupRemoteService.lookupTippBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
		this.vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
		this.wetteStateless = LookupRemoteService.lookupWetteBeanRemote();
		this.vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
		this.forumStateless = LookupRemoteService.lookupForumBeanRemote();
		this.einstellungStateless = LookupRemoteService.lookupEinstellungBeanRemote();
		this.lifegoalsSingleton = LookupRemoteService.lookupLivegoalsSingletonRemote();
	}
}
