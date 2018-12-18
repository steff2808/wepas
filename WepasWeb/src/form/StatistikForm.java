package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tables.StatistikTable;
import util.LookupRemoteService;
import de.wepas.connector.EinstellungBeanRemote;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.WetteBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class StatistikForm
{
	private static Log log = LogFactory.getLog(StatistikForm.class);
	
	private boolean ButtonNavigation;
	private boolean ButtonHighscore;
	private boolean ButtonTreppchen;
	private boolean ButtonSupertreffer;
	private boolean ButtonTreffer;
	private boolean ButtonBlinde;
	
	private boolean ButtonSortname;
	private boolean ButtonSortanzahl;
	private boolean ButtonSortpunkte;
	private boolean ButtonSortboring;
	private boolean ButtonSortkonto;
	private boolean ButtonSortbonus;
	private boolean ButtonSorttagessiege;
	private boolean ButtonSorttendenz;
	private boolean ButtonSorttreffer;
	private boolean ButtonSortexakt;
	private boolean ButtonSortnieten;
	private boolean ButtonSortblind;
	private boolean ButtonSorthunger;
	private boolean ButtonSortknapp;
	private boolean ButtonSortwaldi;
	private boolean ButtonSortnetzerdelling;
	
	private TipperBeanRemote tipperStateless;
	private TippBeanRemote tippStateless;
	private WetteBeanRemote wetteStateless;
	private EinstellungBeanRemote einstellungStateless;
	private HttpServletRequest req;
	
	public StatistikForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.req = req;
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonHighscore = req.getParameter("highscore") != null;
		this.ButtonTreppchen = req.getParameter("treppchen") != null;
		this.ButtonSupertreffer = req.getParameter("supertreffer") != null;
		this.ButtonTreffer = req.getParameter("treffer") != null;
		this.ButtonBlinde = req.getParameter("blinde") != null;
		
		this.ButtonSortname = req.getParameter("sortname") != null;
		this.ButtonSortanzahl = req.getParameter("sortanzahl") != null;
		this.ButtonSortpunkte = req.getParameter("sortpunkte") != null;
		this.ButtonSortboring = req.getParameter("sortboring") != null;
		this.ButtonSortkonto = req.getParameter("sortkonto") != null;
		this.ButtonSortbonus = req.getParameter("sortbonus") != null;
		this.ButtonSorttagessiege = req.getParameter("sorttagessiege") != null;
		this.ButtonSorttendenz = req.getParameter("sorttendenz") != null;
		this.ButtonSorttreffer = req.getParameter("sorttreffer") != null;
		this.ButtonSortexakt = req.getParameter("sortexakt") != null;
		this.ButtonSortnieten = req.getParameter("sortnieten") != null;
		this.ButtonSortblind = req.getParameter("sortblind") != null;
		this.ButtonSorthunger = req.getParameter("sorthunger") != null;
		this.ButtonSortknapp = req.getParameter("sortknapp") != null;
		this.ButtonSortwaldi = req.getParameter("sortwaldi") != null;
		this.ButtonSortnetzerdelling = req.getParameter("sortnetzerdelling") != null;
		
		if (this.ButtonNavigation || this.ButtonHighscore || this.ButtonTreppchen || this.ButtonSupertreffer || this.ButtonTreffer || this.ButtonBlinde)
		{
			req.getSession().setAttribute(ApplConstants.HIGHSCORETABLE, null);
			return;
		}
		
		if (this.isButtonSortname())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortName();
		}
		else if (this.isButtonSortanzahl())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortAnzahl();
		}
		else if (this.isButtonSortpunkte())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortPunkte();
		}
		else if (this.isButtonSortboring())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortBoring();
		}
		else if (this.isButtonSortkonto())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortKonto();
		}
		else if (this.isButtonSortbonus())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortBonus();
		}
		else if (this.isButtonSorttagessiege())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortTagessiege();
		}
		else if (this.isButtonSorttendenz())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortTendenz();
		}
		else if (this.isButtonSorttreffer())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortTreffer();
		}
		else if (this.isButtonSortexakt())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortExakt();
		}
		else if (this.isButtonSortnieten())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortNieten();
		}
		else if (this.isButtonSortblind())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortBlind();
		}
		else if (this.isButtonSorthunger())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortHunger();
		}
		else if (this.isButtonSortknapp())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortKnapp();
		}
		else if (this.isButtonSortwaldi())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortWaldi();
		}
		else if (this.isButtonSortnetzerdelling())
		{
			StatistikTable statistiktable = (StatistikTable) req.getSession().getAttribute(ApplConstants.STATISTIKTABLE);
			statistiktable.sortNetzerdelling();
		}
		else
		{
			this.getResources();
			EnTipper user = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
			req.getSession().setAttribute(ApplConstants.STATISTIKTABLE,
					new StatistikTable(user, wetteStateless, tipperStateless, tippStateless, einstellungStateless));
		}
	}
	
	private void getResources()
	{
		this.tippStateless = LookupRemoteService.lookupTippBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
		this.wetteStateless = LookupRemoteService.lookupWetteBeanRemote();
		this.einstellungStateless = LookupRemoteService.lookupEinstellungBeanRemote();
	}
	
	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
	}
	
	public boolean isButtonHighscore()
	{
		if(ButtonHighscore)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Highscore) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonHighscore;
	}
	
	public boolean isButtonTreppchen()
	{
		if(ButtonTreppchen)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Treppchen) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonTreppchen;
	}
	
	public boolean isButtonSupertreffer()
	{
		if(ButtonSupertreffer)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Supertreffer) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSupertreffer;
	}
	
	public boolean isButtonTreffer()
	{
		if(ButtonTreffer)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Treffer) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonTreffer;
	}
	
	public boolean isButtonBlinde()
	{
		if(ButtonBlinde)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Blinde) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonBlinde;
	}
	
	public boolean isButtonSortname()
	{
		if(ButtonSortname)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->name  ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortname;
	}
	
	public boolean isButtonSortanzahl()
	{
		if(ButtonSortanzahl)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->anzahl) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortanzahl;
	}
	
	public boolean isButtonSortpunkte()
	{
		if(ButtonSortpunkte)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->punkte) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortpunkte;
	}
	
	public boolean isButtonSortboring()
	{
		if(ButtonSortboring)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->boring) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortboring;
	}
	
	public boolean isButtonSortkonto()
	{
		if(ButtonSortkonto)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->konto ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortkonto;
	}
	
	public boolean isButtonSortbonus()
	{
		if(ButtonSortbonus)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->bonus ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortbonus;
	}
	
	public boolean isButtonSorttagessiege()
	{
		if(ButtonSorttagessiege)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->siege ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSorttagessiege;
	}
	
	public boolean isButtonSorttendenz()
	{
		if(ButtonSorttendenz)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->tend  ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSorttendenz;
	}
	
	public boolean isButtonSorttreffer()
	{
		if(ButtonSorttreffer)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->treff ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSorttreffer;
	}
	
	public boolean isButtonSortexakt()
	{
		if(ButtonSortexakt)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->exakt) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortexakt;
	}
	
	public boolean isButtonSortnieten()
	{
		if(ButtonSortnieten)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->nieten) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortnieten;
	}
	
	public boolean isButtonSortblind()
	{
		if(ButtonSortblind)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->blinf ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortblind;
	}
	
	public boolean isButtonSorthunger()
	{
		if(ButtonSorthunger)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->hunger) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSorthunger;
	}
	
	public boolean isButtonSortknapp()
	{
		if(ButtonSortknapp)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->knapp ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortknapp;
	}
	
	public boolean isButtonSortwaldi()
	{
		if(ButtonSortwaldi)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->waldi ) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortwaldi;
	}
	
	public boolean isButtonSortnetzerdelling()
	{
		if(ButtonSortnetzerdelling)
		{
			EnTipper tipper = (EnTipper)req.getSession().getAttribute(ApplConstants.USER);
			log.info("--> (Statistik->netzer) " + tipper.getAtTipperName() + ", " + tipper.getAtTipperVorname());
		}
		return ButtonSortnetzerdelling;
	}
	
}
