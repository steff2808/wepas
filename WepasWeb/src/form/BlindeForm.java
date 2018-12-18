package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tables.BlindeTable;
import util.LookupRemoteService;
import de.wepas.connector.SpielBeanRemote;
import de.wepas.connector.SpieltagBeanRemote;
import de.wepas.connector.TippBeanRemote;
import de.wepas.connector.TipperBeanRemote;
import de.wepas.connector.VereinBeanRemote;
import de.wepas.constants.ApplConstants;
import de.wepas.jpa.EnTipper;

public class BlindeForm
{
	private static Log log = LogFactory.getLog(BlindeForm.class);
	
	private boolean ButtonNavigation;
	private boolean ButtonStatistik;
	private boolean ButtonHighscore;
	private boolean ButtonTreppchen;
	private boolean ButtonSupertreffer;
	private boolean ButtonTreffer;
    private boolean ButtonSortname;
	private boolean ButtonSortspieltag;
    private boolean ButtonSortblindgrad;

    private TipperBeanRemote tipperStateless;
	private TippBeanRemote tippStateless;
	private SpieltagBeanRemote spieltagStateless;
	private SpielBeanRemote spielStateless;
	private VereinBeanRemote vereinStateless;
	private HttpServletRequest req;
	
	public BlindeForm(HttpServletRequest req)
	{
		log.debug("Entering " + this.getClass().getName());
		this.req = req;
		this.ButtonNavigation = req.getParameter("navigation") != null;
		this.ButtonStatistik = req.getParameter("statistik") != null;
		this.ButtonHighscore = req.getParameter("highscore") != null;
		this.ButtonTreppchen = req.getParameter("treppchen") != null;
		this.ButtonSupertreffer = req.getParameter("supertreffer") != null;
		this.ButtonTreffer = req.getParameter("treffer") != null;
		this.ButtonSortname = req.getParameter("sortname") != null;
		this.ButtonSortspieltag = req.getParameter("sortspieltag") != null;
		this.ButtonSortblindgrad = req.getParameter("sortblindgrad") != null;
		if (this.ButtonNavigation || this.ButtonStatistik 
				|| this.ButtonHighscore || this.ButtonTreppchen 
				|| this.ButtonSupertreffer || this.ButtonTreffer)
		{
			req.getSession().setAttribute(ApplConstants.BLINDETABLE, null);
			return;
		}
		if (this.isButtonSortname())
		{
			BlindeTable blindetabletable = (BlindeTable) req.getSession().getAttribute(ApplConstants.BLINDETABLE);
			blindetabletable.sortName();
		}
		else if (this.isButtonSortspieltag())
		{
			BlindeTable blindetabletable = (BlindeTable) req.getSession().getAttribute(ApplConstants.BLINDETABLE);
			blindetabletable.sortSpieltag();
		}
		else if (this.isButtonSortblindgrad())
		{
			BlindeTable blindetabletable = (BlindeTable) req.getSession().getAttribute(ApplConstants.BLINDETABLE);
			blindetabletable.sortBlinde();
		}
		else
		{
			this.getResources();
			EnTipper user = (EnTipper) req.getSession().getAttribute(ApplConstants.USER);
			req.getSession().setAttribute(ApplConstants.BLINDETABLE, new BlindeTable(user, tippStateless, tipperStateless, spielStateless, spieltagStateless, vereinStateless));
		}
  	}
	
	private void getResources()
	{
		this.spielStateless = LookupRemoteService.lookupSpielBeanRemote();
		this.spieltagStateless = LookupRemoteService.lookupSpieltagBeanRemote();
		this.tippStateless = LookupRemoteService.lookupTippBeanRemote();
		this.tipperStateless = LookupRemoteService.lookupTipperBeanRemote();
		this.vereinStateless = LookupRemoteService.lookupVereinBeanRemote();
	}

	public boolean isButtonNavigation()
	{
		return ButtonNavigation;
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
	
	public boolean isButtonSortname()
	{
		return ButtonSortname;
	}

	public boolean isButtonSortspieltag()
	{
		return ButtonSortspieltag;
	}

	public boolean isButtonSortblindgrad()
	{
		return ButtonSortblindgrad;
	}
}
